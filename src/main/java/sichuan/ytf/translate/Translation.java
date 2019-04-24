package sichuan.ytf.translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Translation {
    static Map<String, String> map = new HashMap<>();

    @Test
    public void test() {
        
        String cn2 = "企业名称,许可证号,法定代表人,地址,许可项目,发证机关,发证日期,有效期至,状态说明";

        
        
        
        
        
        
        
        String a = "科室地址,生产（经营）地址,许可证号,日常监督管理人员,许可范围,有效期起,许可证有效性,注册地址,有效期至,许可证类别及科室名称,日常监管人员,企业负责人,投诉举报电话,生产地址,食品类别,地址,有效期止,主体类型,经营者名称,医疗机构类别,日常监督管理机构,许可证编号,分类码,社会信用代码,法定代表人(负责任人),发证机关,医疗机构名称,日常监管机构,生产（经营）范围,备注,经济性质,外设仓库地址,住所,发证日期,主体业态,签发人,质量负责人,经营类别,生产地址和范围,经营场所,行政区域,法定代表人(负责人),企业名称,科室负责人,经营项目,法定代表人,GMP证书,产品名称,负责人,状态说明,生产者名称";
        String b = "departmentAddress,productionAddress,licenseNumber,dailySupervisionManagementPersonnel,licenseScope,validityLicenseStart,validityLicense,registrationAddress,expirationDate,licenseCategoryDepartmentName,dailySupervisor,enterprisePersonCharge,complaintReportingTelephone,productionAddress,foodCategory,address,validityLicenseEnd,mainBodyType,operatorName,medicalInstitutionCategory,dailySupervisionManagementOrganization,licenseNumber,classificationCode,socialCreditCode,legalRepresentative,licensingAuthority,medicalInstitutionName,dailySupervisoryAuthority,productionScope,notes,economicNature,externalWarehouseAddress,residence,issuingDate,mainBusinessForm,issuingPersion,qualityPersonCharge,businessCategory,productionAddressScope,businessPlace,administrativeRegion,legalRepresentative,enterpriseName,departmentHead,businessProject,legalRepresentative,GMPCertificate,productName,personCharge,statusDescription,producerName";

        a += ",xxxxx";
        b += ",xxxxx";
        a += ",医疗单位,主治功能或适应症";
        b += ",medicalUnit,primaryFunctionOrIndication";
        a += ",有效日期起,有效日期止,GSP许可证有效日期起,GSP许可证有效日期止";
        b += ",validDateBegin,validDateEnd,GSPvalidDateBegin,GSPvalidDateEnd";
        a += ",法人 代表,开办企业类别,许可证(备案)号";
        b += ",corporater,startUpBusinessCategory,licenseNumber";
        a += ",产品英文名称,产品注册号,注册类别,产品使用范围";
        b += ",productNameEn,productRegisterNo,registerType,productUsedScope";
        a += ",药包材名称,使用企业,使用企业所在地区,产品质量标准名称,注册编号";
        b += ",pharmaceuticalPackageName,useEnterpry,useEnterpryArea,productQualityStandardName,registrationNumber";
        a += ",英文\\拉丁名,是否处方药,适应症或主治功能,药用辅料名称";
        b += ",nameEn,prescribeDrug,primaryFunction,medicinalExcipientName";
        a += ",卫产许可证号,所在地区";
        b += ",healthProductionLicenseNumber,country";
        a += ",商品名称,汉语拼英,商标,有效期至止,功效成分,适宜人群,储藏方式,注意事项,用法用量";
        b += ",commodityName,chineseSpelling,trademark,expirationDate,efficacyIngredients,suitablePeople,storageMethod,mattersNeedingAttention,usageDosage";
        a += ",企业所在地区,生产品种,QS证号";
        b += ",enterpriseLocation,productionVarieties,QSCertificateNumber";
        a += ",登载日期,委托方,受托方";
        b += ",launchDate,principal,trustee";
        a += ",企业类别,营业执照号,质量代表人,ip地址";
        b += ",enterpriseType,businessLicenseNo,qualityRepresentative,ipAddress";
        a += ",生产加工者名称,生产加工地址";
        b += ",processorManufacturerName,processingManufacturingAddress";
        a += ",类型类别,配置范围,经营者(负责人)姓名";
        b += ",typeCategory,configurationScope,managerName";
        a += ",科室名称,许可证类别";
        b += ",departmentName,licenceCategory";
        a += ",范围,非法人变更为法人";
        b += ",scpoe,changeCorporater";
        a += ",英文地址,英文范围,英文发证机关";
        b += ",addressEn,scopeEn,licensingAuthorityEn";
        a += ",证书名称,类别,变更(1)";
        b += ",certName,category,change";
        a += ",业务编码,销售类型";
        b += ",businessNo,saleType";
        a += ",生产企业名称,准产注册证号,首次重新注册,重新注册品种原注册证号";
        b += ",productionEnterpriseName,quasiProductionRegistrationCertificateNumber,firstReregistration,reregistrationVarietiesOriginalRegistrationCertificateNumber";
        a += ",产品备案号,是否受托生产";
        b += ",productRecordNumber,fiduciaryProduction";
        a += ",服务范围,省辖市,生产类别";
        b += ",serviceScope,provincialMunicipality,productCategory";
        a += ",签 发 人,机构地址,主服务器域名,主服务器地址,经营性质,网站负责人";
        b += ",issuer,institutionalAddress,mainServerDomainName,mainServerAddress,businessNature,webSiteManager";
        a += ",登记证编号,小作坊名称,产品品种,登记日期,登记机关";
        b += ",registrationCertificateNumber,smallWorkshopName,productVariety,registrationDate,registrationAuthority";
        a += ",食品食品添加剂类别,产品类别编号,产品类别名称,产品明细";
        b += ",foodAdditivesCategory,productCategoryNumber,productCategoryName,productDetails";
        a += ",食品、食品添加剂类别,类别编号,类别名称,品种明细,产品名称及执行标准";
        b += ",category,categoryNumber,categoryName,categoryDetail,productNameAndExecutiveStandard";
        a += ",原证书编号";
        b += ",oldCertificateNumber";
        a += ",GSP证书号,GSP证书有效期起至时间";
        b += ",GSPCertNo,GSPCertValidityPeriod";
        a += ",是否是体外诊断试剂,产品分类名称,日期";
        b += ",vitroDiagnosticReagent,productClassificationName,date";
        a += ",汉语拼音,英文名/拉丁名,曾用名,原批准单位,注册分类,包装,批准文号有效期,包装标签,药品说明书";
        b += ",chinesePinyin,englishNameOrLatinName,usedName,formerApprovalUnit,registrationClassification,packaging,validityPeriodApprovalNumber,packagingLabel,pharmaceuticalInstructions";
        a += ",生产国（地区）,生产企业（中文）,生产企业（英文）,在华申报责任单位,在华责任单位地址";
        b += ",productCountry,productionEnterpriseCn,productionEnterpriseEn,chinaDeclareResponsibleUnits,ChinaResponsibleUnitsAddress";
        a += ",生产企业地址,批件状态,批件有效期,卫生许可证号,产品名称备注,产品技术要求";
        b += ",productionEnterprisesAddress,batchStatu,batchvValidityPeriodDate,hygieneLicenseNumber,productNameAnnotation,productsTechnicalRequirement";
        a += ",经营范围(2002分类),经营范围(2017分类)";
        b += ",operationScope2002,operationScope2017";
        a += ",生产产品登记表";
        b += ",productRegistrationForm";
        a += ",产品名称或产品分类名称,型号/规格或包装规格,产品描述或主要组成成份";
        b += ",productNameOrProductTypeName,modelSpecificationOrPackageSpecification,productDescriptionOrMainComponents";
        a += ",生产厂商名称（英文）,生产厂地址（中文）,注册代理";
        b += ",manufacturerNameEn,manufacturerAddressCn,registerProxy";
        a += ",生产国或地区（英文）,生产厂商名称（中文）,生产国或地区（中文）,售后服务机构";
        b += ",countryOrRegionEn,manufacturerNameCn,countryOrRegionCn,aftersalesServiceOrganization";
        a += ",型号、规格,结构及组成,适用范围,产品标准,邮编,主要组成成分（体外诊断试剂）,预期用途（体外诊断试剂）,产品储存条件及有效期（体外诊断试剂）";
        b += ",modelSpecification,structureComposition,applicationScope,productStandard,zipCode,mainComponents,expectedUse,storageConditionsAndExpiryDate";
        a += ",保护品种编号,药品名称,公告号,保护级别,保护起始日,保护终止日,保护期限";
        b += ",protectionSpeciesNumber,drugName,bulletinNumber,protectionLevel,protectionStartDate,protectionTerminationDate,protectionPeriod";
        a += ",品种名称,产品来源,法人代表";
        b += ",varietiesName,productSource,corporater";
        a += ",省市,批准延续日期,有效期延续至,批准延续的认证范围,认证GMP版本";
        b += ",provinces,approvalExtensionDate,validityPeriodExtendDate,approvalExtensionCertificationScope,certificationGMPVersion";
        a += ",社会信用代码/组织机构代码";
        b += ",socialCreditCodeOrOrganizationalCode";
        a += ",原注册证号,注册证号备注,分包装批准文号,公司名称（中文）,公司名称（英文）,地址（中文）,地址（英文）,国家/地区（中文）,国家/地区（英文）,产品名称（中文）,产品名称（英文）,商品名（中文）,商品名（英文）,"
                + "剂型（中文）,规格（中文）,包装规格（中文）,生产厂商（中文）,生产厂商（英文）,厂商地址（中文）,厂商地址（英文）,厂商国家/地区（中文）,厂商国家/地区（英文）,"
                + "有效期截止日,分包装企业名称,分包装企业地址,分包装文号批准日期,分包装文号有效期截止日";
        b += ",originRegisterNumber,registerNumberNote,subpackageApprovalNumber,companyNameCn,companyNameEn,addressCn,addressEn,countryOrRegionCn,countryOrRegionEn,productNameCn,productNameEn,goodsNameCn,goodsNameEn,"
                + "dosageFormCn,specificationCn,packageSpecification,manufacturerCn,manufacturerEn,manufacturerAddressCn,manufacturerAddressEn,manufacturerCountryCn,manufacturerCountryEn,"
                + "expiryDate,subpackageEnterpriseName,subpackageEnterpriseAddress,subpackageApprivalNumberApprivalDate,subpackageApprivalNumberExpiryDate";
        a += ",使用备案号,备案状态,中药提取物信息,批准文号备注";
        b += ",useRecordNumber,recordStatus,chineseMedicineExtractsInformation,approvalNumberRemark";
        a += ",英文名称/拉丁名称,药品分类,药品标准,备案内容";
        b += ",englishNameOrLatinName,drugClassification,drugStandard,recordContent";
        a += ",药品本位码,药品本位码备注";
        b += ",drugStandardCode,drugStandardCodeRemark";
        a += ",传统中药制剂名称,配制工艺路线,医疗机构注册地址";
        b += ",traditionalChineseMedicinePreparationsName,processingRoute,medicalInstitutionsRegistrationAddress";
        a += ",产品注册证号";
        b += ",productRegisterNumber";
        a += ",备案人名称(中文),备案人名称(原文),备案人名称(英文),代理人,代理人注册地址,产品名称中文,产品名称原文,产品名称英文,结构特征,分类编码,备案办理人,首次备案/备案变更/取消备案日期,状态,备案变更次数,备案类型,第一类医疗器械生产备案号,是否已列入生产备案凭证的产品列表";
        b += ",recordHolderNameCn,recordHolderName,recordHolderNameEn,agent,agentRegistrationAddress,productNameCn,productName,productNameEn,structuralCharacteristics,classificationCode,filingAgent,firstOrChangeOrCancelFilingDate,status,filingChangesCount,filingType,firstTypeMedicalDevicesProductionFilingNumber,productionFilingProductsDocuments";
        a += ",内部受理号,统一审批编码,受理日期,企业注册地址,生产场所地址,注册证到期日期,产品储存条件及有效期,申请类型,产品分类,变更日期,变更内容,变更备注";
        b += ",internalAcceptanceNumber,uniformApprovalCode,acceptanceDate,enterpriseRegistrationAddress,productionSiteAddress,registrationCertificateMaturityDate,productStorageConditionsValidityDate,applicationType,productClassification,changeDate,changeContent,changeNote";
        a += ",网站IP地址,服务器存放地址,非经营性互联网信息服务备案编号";
        b += ",websiteIPAddress,serverStorageAddress,non-commercialInternetInformationServiceRecordNumber";
        a += ",最近一次备案日期,产品登记表,日常监督监管人员";
        b += ",lastFilingDate,productRegistrationForm,dailyDupervisorsSupervisors";
        a += ",注册地址区县,注册街道,许可名称,发证机构,证书状态,GSP认证期至";
        b += ",registeredAddressDistrict,registeredStreet,licenseName,licensingInstitution,certificateStatus,GSPCertificationPeriod";
        a += ",企业场所所在区,许可证(备案凭证)名称,许可证(备案凭证)编号,经营模式,监管分级,监管分类";
        b += ",enterpriseArea,licenseName,licenseNumber,operationMode,supervisionGrading,supervisionClassification";
        a += ",产品注册文号,药包材产品名称,产品规格";
        b += ",poductRegistrationNumber,productNamePharmaceuticalPackage,productSpecification";
        a += ",受理号,批件号,原始编号（原批准文号）,标准";
        b += ",acceptanceNumber,batchNumber,originalNumber,standard";
        a += ",管辖区域,证书有效期至";
        b += ",jurisdictionArea,certificateValidityUntil";
        a += ",电信业务经营许可证ICP备案号";
        b += ",telecomBusinessLicenseICPRecordNumber";
        a += ",辖区,变更及注销记录";
        b += ",jurisdiction, alterationCancellationRecords";
        a += ",区县,最近变更时间,生产范围(副本),质量受权人";
        b += ",district,recentChangeTime,ProductionScope,qualityAuthorizedPerson";
        a += ",所属区县,联系电话";
        b += ",district,telephone";
        a += ",发证/备案机关,发证/备案时间";
        b += ",issuingFilingOrgan,issuingFilingTime";
        a += ",制剂配制单位名称,文号有效至";
        b += ",namePreparationUnit,validityPeriodDate";
        a += ",药品生产企业,药品通用名称,文号有效期";
        b += ",pharmaceuticalManufacturingEnterprises,genericNamesDrugs,validityPeriodNumbers";
        a += ",监督举报电话";
        b += ",supervisoryReportingTelephone";
        a += ",产品性能结构及组成,产品适用范围,生产场所";
        b += ",productPerformanceStructureComposition,productScopeApplication,productionSite";
        a += ",许可证有效起始日期,证有效截止日期,备案凭证编号";
        b += ",licenseValidStartDate,certificateValidDeadline,recordDocumentsNumber";
        a += ",生产企业,编号,生产批号,企业许可证,合同编号,出口企业名称,出口企业地址,销往国家,生产数量,企业联系人";
        b += ",manufacturingEnterprise,number,batchNumber,license,contractNumber,exportEnterpriseName,exportEnterpriseAddress,countrySold,productionQuantity,enterpriseContactPerson";
        a += ",证书号,认证范围,管理类别,注销机关,质量管理人";
        b += ",certNo,certificationArea,managerType,cancellationAuthority,qualityAdministrator";
        a += ",批准机构,批准机关";
        b += ",approvingBody,approvalAuthority ";
        a += ",有效截止日期,产品类型,审批机关";
        b += ",effectiveDeadline,productType,approvalAuthority";
        a += ",委托企业,卫生许可证,委托企业地址,委托企业卫生许可证号,当日序号";
        b += ",entrustedEnterprise,healthLicense,entrustedEnterpriseAddress,entrustedEnterpriseHealthLicenseNumber,dateSequenceNumber";
        a += ",附件";
        b += ",enclosure";
        a += ",企业地址,许可生效期,许可截止期";
        b += ",enterpriseAddress,licensingEffectivePeriod,licensingDeadline";
        a += ",地市,新许可证号,组织机构代码,企业类型";
        b += ",city,newLicenseNumber,organizationalCode,enterpriseType";
        a += ",机构名称,所在地市,研究范围";
        b += ",organizationName,locationCity,studyScope";
        a += ",商品名,英文名称,有效日期,原批准文号,执行标准";
        b += ",commodityName,englishName,validDate,originalApprovalNumber,implementationStandard";
        a += ",通用名称,生产厂商,登记证书号,规格,药品批准文号";
        b += ",generalName,manufacturer,registrationCertificateNumber,specification,drugApprovalNumber";
        a += ",证书有效期";
        b += ",certValidityPeriod";
        a += ",社会信用代码(身份证号码),许可明细";
        b += ",socialCreditCode,licenseDetail";
        a += ",社会信用代码（身份证号码）,法定代表人（负责人）";
        b += ",socialCreditCode,legalPerson";
        a += ",企业名称（名称）";
        b += ",enterpriseName";
        a += ",食品类型,截止日期,仓储地址";
        b += ",foodType,deadline,storageAddress";
        a += ",社会信用代码（身份证号）,产品品种名细,有效状态";
        b += ",socialCreditCode,productVarietiesDetail,effectiveStatus";
        a += ",发证日期证日期,许可证截止日期";
        b += ",issuanceDate,licenceDeadline";
        a += ",法定代表负责人,监管机构,监管人员";
        b += ",principalLegalRepresentative,supervisoryAuthority,supervisor";
        a += ",生产厂家,生产厂家所在地区,产品标准号,生产许可证编号,产地,食品添加剂,食用方法,贮存条件,保质期,配料";
        b += ",manufacturers,areasManufacturersLocated,productStandardNumber,productionLicenseNumber,origin,foodAdditives,eatingMethods,storageConditions,shelfLife,ingredients";
        a += ",许可类别,许可证类型,许可类别说明,食品安全管理员,负责人名";
        b += ",licenseType,licenseCertType,licenseTypeDesc,foodSafetyAdmin,personChargeName";
        a += ",负 责 人";
        b += ",personCharge";
        a += ",产品类别,*发证机关";
        b += ",productType,licensingAuthority";
        a += ",品名,申报单位,配制单位";
        b += ",name,declarationUnit,preparationUnit";
        a += ",地区";
        b += ",region";
        a += ",持有者名称,保健功能,标志性成分名称及含量,主要原料,再注册情况";
        b += ",holderName,healthCareFunction,markerIngredientsName,mainRawMaterials,reregistrationStatus";
        a += ",省份,明细,有效期,说明";
        b += ",provinces,details,validityPeriod,description";
        a += ",注销原因,注销日期";
        b += ",cancellationReasons,cancellationDate";
        a += ",证书编号,检验方式,有限期至,发证单位";
        b += ",certificateNumber,inspectionMethod,limitedPeriod,licensingUnit";
        a += ",法定代表人或主要负责人,网站ip地址,电信业务经营许可证或者非经营性互联网信息服务备案编号,医疗器械网络交易服务第三方平台备案凭证编号";
        b += ",legalPerson,ip,internetRecodeNo,servicePlatformRecodeNo";
        a += ",发证/备案日期";
        b += ",recordDate";

        a += ",法人,邮政编码,变更项目,变更后内容,变更时间";
        b += ",legalPerson,postalCode,changeItem,changeContent,changeTime";
        a += ",企业地市,审批年度";
        b += ",enterprisePrefecture,approvalYear";
        a += ",注册证号,生产单位";
        b += ",registrationCertificateNumber,productionUnit";
        a += ",许可期限,备 注";
        b += ",licensePeriod,remark";
        a += ",注册地址,配制地址,法 定 代表人,制 剂 负责人,医疗机构 类 别,配制范围,许可证号";
        b += ",registrationAddress,preparingAddress,legalRepresentative,preparatoryOfficer,medicalInstitutionCategory,preparingScope,licenseNumber";
        a += ",有效期自";
        b += ",validityPeriodStart";
        a += ",注册号,批准日期,批准有效期,发证机关,产品中文名称,产品品种编码,规格型号,注册商名称,注册厂家,注册商区域,产品结构及组成,产品主要功能,产品标准编号";
        b += ",registrationNumber,approvalDate,approvalValidityPeriod,licensingAuthority,productChineseName,productVarietyCode,specificationModel,registererName,registeredManufacturer,registererArea,productStructurecomposition,productMainFunctions,productStandardNumber";
        a += ",企业地区名称,生产负责人";
        b += ",enterpriseAreaName,productLeader";
        a += ",企业所在地区名称,生产许可证号,许可机关";
        b += ",enterpriseAreaName,productionLicenseNumber,licensingAuthority";
        a += ",配置地址,配制范围,业务负责人";
        b += ",configurationAddress,configurationScope,businessLeader";
        a += ",GSP证号,GSP发证日期,GSP有效期至";
        b += ",GSPCertificateNo,GSPIssuingCertificateDate,GSPCertificateExpirationDate";
        a += ",地区名称,许可证发证日期,许可证有效日期止,生产地址及生产范围,相关数据库查询,注";
        b += ",areaName,licenseIssuingDate,licenseValidityDate,productionAddressScopeProduction,relevantDatabaseQuery,note";

        a += ",注册证编号,注册人名称,注册人住所,代理人名称,代理人住所,型号、规格/包装规格,结构及组成/主要组成成分,适用范围/预期用途,产品储存条件及有效期(体外诊断试剂适用),其他内容,审批部门,批证日期,变更信息";
        b += ",registrationCertificateNumber,registrarName,registrarResidence,agentName,agentResidence,model,specificationOrPackagingSpecification,applicableScopeOrExpectedUse,productStorageConditionsValidityPeriod,otherContents,approvalDepartment,approvalDate,changeInformation";
        a += ",备案人名称,产品类名称,包装规格,产品有效期,主要组成成分";
        b += ",nameFiler,nameProductCategory,packingSpecification,validityPeriodProduct,mainComponents";
        a += ",备案人组织机构代码,备案人注册地址,型号/规格,产品描述,预期用途,变更情况,检查监督情况";
        b += ",organizationalCodeRecorder,registrationAddress,modelSpecification,productDescription,expectedUse,change,inspectionSupervisionRecorder";
        a += ",变更记录,许可证状态";
        b += ",changeRecord,licenseStatus";
        a += ",生产范围,备案单位,产品信息";
        b += ",productionScope,recordingUnit,productInformation";
        a += ",备案编号,备案部门,备案日期";
        b += ",recordNumber,recordDepartment,recordDate";
        a += ",库房地址,发证部门,有效期限";
        b += ",warehouseAddress,issuingDepartment,validityPeriod";
        a += ",企业名称,标准名称,标准编号,标准备案编号,备案时间,备案有效期,标准全文和修订单";
        b += ",enterpriseName,standardName,standardNumber,standardFilingNumber,filingTime,filingValidityPeriod,standardFullTextRevisionForm";
        a += ",单位名称,法定代表人或业主,证件状态";
        b += ",unitName,legalRepresentativeOwner,certificateStatus";
        a += ",生产地址和生产范围";
        b += ",productionAddressAndScope";

        a += ",GSP证书,GSP证书发证日期,GSP证书有效期至";
        b += ",GSPCertificate,issuingCertificateDate,certificateExpirationDate";
        a += ",制剂室负责人";
        b += ",headPharmaceuticalDepartment";
        a += ",配制地址和配制范围";
        b += ",configurationAddressAndscope ";
        a += ",仓库地址,经营方式,经营范围,GSP证书编号,GSP认证日期,GSP证书有效期";
        b += ",warehouseAddress,modeOperation,businessScope,GSPCertificateNumber,GSPCertificationDate,GSPCertificateValidityPeriod";

        a += ",批准文号,制剂名称,配制单位所属辖区,制剂类别,剂型,规格,配制单位名称";
        b += ",approvalNumber,preparationName,jurisdictionPreparationUnit,categoryPreparation,dosageForm,specification,preparationUnitName";

        a += ",主要负责人,医疗器械质量安全管理人,办公场所,网站名称,网站域名,网络客户端应用程序名,现场检查结果";
        b += ",responsiblePerson,medicalDeviceQualitySafetyAdministrator,officeSpace,websiteName,websiteDomainName,networkClientApplicationName,onSiteInspectionResults";

        a += ",序号,备案号,备案药品生产企业名称,调剂毒性中药饮品片品种名称,备案有效期";
        b += ",serialNumber,recordNumber,recordDrugManufacturerName,varietyDispensingToxicChineseMedicineDrinkName,filingTime";
        a += ",许可项目";
        b += ",licensingProject";
        a += ",经营场所或生产场所,医疗器械网络销售类型,医疗器械生产（经营）许可证或备案凭证编号,医疗器械网络交易服务第三方平台名称";
        b += ",businessPlaceOrProductionPlace,networkSalesMedicalDevicesType,numberLicenseOrRecordCertificateProductionMedicalDevices,thirdPartyPlatformName";
        a += ",域名,IP地址,电信业务经营许可证号,法定代表人或负责人";
        b += ",domain,IPAddress,telecommunicationsBusinessLicenseNumber,legalRepresentativePersonCharge";
        a += ",备案人,备案人地址,备案结论";
        b += ",filer,addressFiler,conclusionFiling";
        a += ",备案机关,经营负责人姓名,食品安全管理人姓名,经营范围和方式,经营地址";
        b += ",recordFilingOrgans,namesBusinessLeaders,foodSafetyAdministratorName,businessScopeMethods,businessAddresses";
        String[] names = a.split(",");
        String[] nameEns = b.split(",");
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], nameEns[i]);
        }
        System.out.println("\n");

        if (true) {
            String[] names2 = cn2.split(",");
            System.out.println(cn2);
            List<String> unTranslate = new ArrayList<>();
            for (int i = 0; i < names2.length; i++) {
                String nameEn = map.get(names2[i]);
                if (nameEn == null || nameEn.length() == 0) {
                    nameEn = "xxxxxxxxxxxxxxxxxx" + names2[i];
                    unTranslate.add(names2[i]);
                }
                if ((i + 1) == names2.length) {
                    System.out.print(nameEn);
                } else {
                    System.out.print(nameEn + ",");

                }
            }
            System.out.println();
            for (String string : unTranslate) {
                System.out.print(string + ",");
            }

            System.out.println("\n");

            System.out.println();
            System.out.println(names.length);
            System.out.println(nameEns.length);
            System.out.println(map);
//            for (int i = 0; i < names.length; i++) {
//                System.out.print(names[i] + "=" + nameEns[i]);
//            }

        }

    }

    public static void main(String[] args) {

    }

}
