package sichuan.ytf.web;
/**
 * 异常状态枚举
 */
public enum ExceptionEnum {
	/** 以下为公共异常 */
	/**
	 * 参数校验相关
	 */
	PARAM_IS_NULL   	                     	(4001, "参数为空"),
	PARAM_FORMAT_ERROR                        	(4002, "数据类型错误"),
	
	/**
	 * 数据库相关
	 */
	CONNECT_DB_ERROR                           	(5001, "连接数据库失败"),
	OPERATE_DB_ERROR							(5002, "操作数据库失败"),
	
	
	/** 以下为通道管理系统相关异常定义 */
	/**
	 * 参数校验相关
	 */
    USER_INCORRECT								(4201, "用户私钥证书信息错误"),
    ADMIN_NOT_FOUND                    			(4202, "参与方未找到"),
    ADMIN_ALREADY_EXIST              			(4203, "参与方已存在"),
    CHANNEL_NOT_FOUND                  			(4204, "通道未找到"),
    CHANNEL_ALREADY_EXIST              			(4205, "通道已存在"),
    CHANNEL_UPDATE_NOT_FOUND             		(4206, "通道更新未找到"),
    CHANNEL_UPDATE_ALREADY_EXIST             	(4207, "通道更新已存在"),
    CHANNEL_UPDATE_ALREADY_SENT             	(4208, "通道更新已发送"),
    CHANNEL_UPDATE_ALREADY_PROCESSED            (4209, "通道更新已处理"),
    CHANNEL_UPDATE_ACTION_INVALID             	(4210, "通道更新动作无效，只能为 add/remove/modify"),
    CHANNEL_UPDATE_NOTHING_TO_DO  			    (4211, "通道无需更新"),
    CHANNEL_UPDATE_INVALID  			        (4212, "通道更新无效"),
    GENESIS_BLOCK_NOT_FOUND				        (4213, "排序节点配置区块未找到"),
    GENESIS_BLOCK_ALREADY_EXIST				    (4214, "排序节点配置区块已存在"),
    MSP_NOT_FOUND                    			(4215, "MSP未找到"),
    MSP_ALREADY_EXIST              				(4216, "MSP已存在"),
    NODE_NAME_INVALID							(4217, "节点名称错误，与TLS证书CN不一致"),
    NODE_URL_INVALID							(4218, "节点URL格式错误"),
    NODE_CERT_INVALID							(4219, "节点证书格式错误"),
    ORDERER_NOT_FOUND                  			(4220, "排序节点未找到"),
    ORDERER_ALREADY_EXIST              			(4221, "排序节点已存在"),
    PEER_NOT_FOUND                  			(4222, "peer节点未找到"),
    PEER_ALREADY_EXIST              			(4223, "peer节点已存在"),
    
    /**
     * 签名相关
     */
    SIGNATURE_ERROR                    			(4301, "签名失败"),
    SIGNATURE_VERIFY_ERROR                    	(4302, "签名校验失败"),
    
    /**
     * fabric manager 相关
     */
    CHANNEL_CONFIG_ERROR						(4401, "通道配置异常"),
    CLIENT_CONFIG_ERROR							(4402, "Fabric Client配置异常"),
    ORDERER_GENESIS_CONFIG_ERROR				(4403, "排序节点配置区块配置异常"),
    
    /**
     * 连接错误
     */
    CONNECT_ADMIN_ERROR							(4501, "连接其他参与方失败"),
    
    ;
    
    private Integer code;
    private String content;
    private ExceptionEnum(Integer code,String content) {
        this.code = code;
        this.content = content;
    }
    public Integer getCode() {
        return code;
    }
    public String getContent() {
        return content;
    }
}

// end
