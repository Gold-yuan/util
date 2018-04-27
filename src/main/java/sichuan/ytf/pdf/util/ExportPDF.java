package sichuan.ytf.pdf.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import sichuan.ytf.usual.util.Base64Util;


public class ExportPDF {

	public String exportPDF(String[] imgStrs, String text) {
		String[] title = {"预测结果查看", "整体总收入预测结果曲线", "整体可供吨公里变化曲线"};
		String[] imgPath = new String[imgStrs.length];
		// 对字节数组字符串进行Base64解码并生成图片
		try {
			for(int j = 0 ; j <imgStrs.length ; j++) {
				String imgStr = imgStrs[j].substring("data:image/png;base64,".length());
				// Base64解码
				byte[] b = Base64Util.decodeBuffer(imgStr);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
				// 生成jpeg图片
				String imgFilePath = "temp"+System.currentTimeMillis()+".png";// 新生成的图片
				File file = new File(imgFilePath);
				imgPath[j] = file.getAbsolutePath();
				OutputStream out = new FileOutputStream(file);
				out.write(b);
				out.flush();
				out.close();
				Thread.sleep(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		File pdfFile = new File("预测结果查看"+System.currentTimeMillis()+".pdf");
		// 1.新建document对象
		Document document = new Document(PageSize.A4);
		try {
			// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
			// 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(pdfFile));

			// 3.打开文档
			document.open();
			// 设置中文字体
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			//中文字体
			Font titleFont = new Font(bfChinese, 14, Font.BOLD);
			Font th = new Font(bfChinese);
			th.setSize(10);

			// 添加内容 文字-图片
			for(int i = 0; i < imgPath.length; i++){

				// 4.添加一个内容段落
				Paragraph titlep = new Paragraph(title[i], titleFont);
				document.add(titlep);
				
				// 添加一个表格
				if(i==0) {
					// 7列的表.
					PdfPTable table = new PdfPTable(7);

			        table.setWidthPercentage(100); // 宽度100%填充
			        table.setSpacingBefore(10f); // 前间距
			        table.setSpacingAfter(10f); // 后间距

			        List<PdfPRow> listRow = table.getRows();
					// 设置列宽
					float[] columnWidths = { 5f, 4f, 4f, 4f, 4f, 4f, 4f};
					table.setWidths(columnWidths);
			        
			        String[] rowsStr = text.split("\\|");
					for (int x = 0; x < rowsStr.length; x++) {
						String[] cellsStr = rowsStr[x].split("!");
						// 行
						PdfPCell cells[] = new PdfPCell[cellsStr.length];
						PdfPRow row = new PdfPRow(cells);
						for (int z = 0; z < cellsStr.length; z++) {
							// 单元格
							cells[z] = new PdfPCell(new Paragraph(cellsStr[z], th));// 单元格内容
							cells[z].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
						}
						// 把第一行添加到集合
						listRow.add(row);
					}
			        //把表格添加到文件中
			        document.add(table);
				}
				
				// 图片
				Image container = Image.getInstance(imgPath[i]);
				container.scalePercent(getPercent2(container.getWidth()));
				container.setAlignment(Element.ALIGN_CENTER);
				document.add(container);
			}
			document.close();
			writer.flush();
			writer.close();
			return pdfFile.getAbsolutePath();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			for(String path:imgPath) {
				File file = new File(path);
				if(file != null && file.isFile() && file.exists()) {
					file.delete();
				}
			}
		}
		return null;
	}

    /** 
     * 第一种解决方案 
     * 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩 
     * @param h 
     * @param w 
     * @return 
     */  
      
	public int getPercent(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		if (h > w) {
			p2 = 297 / h * 100;
		} else {
			p2 = 210 / w * 100;
		}
		p = Math.round(p2);
		return p;
	}

    /** 
     * 第二种解决方案，统一按照宽度压缩 
     * 这样来的效果是，所有图片的宽度是相等的，自我认为给客户的效果是最好的 
     * @param args 
     */  
	public int getPercent2(float w) {
		int p = 0;
		float p2 = 0.0f;
		p2 = 530 / w * 100;
		p = Math.round(p2);
		return p;
	}
}
