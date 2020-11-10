package com.opkcloud;


/**
 * 通用返回码常量类,项目自定义业务coder可集成该类,建议从1000开始
 *
 * @author zhousir
 * @since 1.0.0-RELEASE
 */
public class ResCoder {

    public final static Coder FAIL = new Coder("9999", "failure", "failure", "失败", "失敗");
    public final static Coder SUCCESS = new Coder("20000", "success", "success", "成功", "成功");
    public final static Coder HANDLE = new Coder("0010", "Processing", "Processing", "处理中", "處理中");
    public final static Coder UN_LOGIN = new Coder("0020", "Not logged in to the system.", "Not logged in to the system.", "未登录系统", "未登錄系統");
    public final static Coder UN_AUTH = new Coder("0030", "UnAuthorized", "UnAuthorized", "未经授权", "未經授權");
    public final static Coder SYS_ERROR = new Coder("0040", "The system is busy.", "The system is busy.", "系统繁忙", "系統繁忙");
    public final static Coder PARAM_ERROR = new Coder("0050", "Parameter error.", "Parameter error.", "参数错误", "參數錯誤");
    public final static Coder DATA_EXIST = new Coder("0060", "Data already exists.", "Data already exists.", "数据已存在", "數據已存在");
    public final static Coder DATA_NOT_EXIST = new Coder("0070", "Data not empty or not exist.", "Data not empty or not exist.", "数据不存在或数据不为空", "數據不存在或不爲空");
    public final static Coder TOKEN_EXPIRE = new Coder("0071", "The token has expired.", "The token has expired.", "令牌已过期", "令牌已過期");
    public final static Coder DISABLE_LOGIN = new Coder("0072", "Account is disabled.", "Account is disabled.", "账号被禁用", "賬號被禁用");
    public final static Coder PERMISSION_DENIED = new Coder("0073", "Insufficient permissions.", "Insufficient permissions.", "权限不足", "權限不足");
    public final static Coder DATA_TOO_LONG = new Coder("0074", "Insufficient permissions.", "Insufficient permissions.", "权限不足", "權限不足");
    public static final Coder INVALID_MOBILE = new Coder("0075", "Invalid mobile number.", "Invalid mobile number.", "手机号码无效", "手機號碼無效");
    public static final Coder FILE_UPLOAD_ERROR = new Coder("0076", "Upload file take error, try again later.", "Upload file take error, try again later.", "上传文件时出错，请稍后再试", "上傳文件時出錯，請稍後再試");
    public static final Coder SEND_CODE_ERROR = new Coder("0077", "Send code take error, try again later.", "Send code take error, try again later.", "验证码发送失败，请重新获取", "驗證碼發送失敗，請重新獲取");
    public static final Coder PLAN_NOT_EXIST = new Coder("0078", "Plan not exist or deleted.", "Plan not exist or deleted.", "计划不存在或已删除.", "計劃不存在或已刪除.");
    public static final Coder IMPORT_LIMIT_NUM = new Coder("0079", "The amount of data is too large, a sub-table can import up to {0} data", "The amount of data is too large, a sub-table can import up to {0} data", "数据量过大，一个子表最多导入{0}条数据", "數據量過大，一個子表最多導入{0}條數據");
    public static final Coder TEMPLATE_ERROR = new Coder("0080", "Template error", "Template error", "模板错误", "模板錯誤");
    public static final Coder REQUEST_REJECT = new Coder("0081", "Illegal request", "Illegal request", "非法请求", "非法請求");
    public static final Coder OPERATION_FAILED = new Coder("0082", "Operation failed", "Operation failed", "操作失败，请刷新重试", "操作失敗，請刷新重試");
    public static final Coder UPLOAD_FAILED = new Coder("0083", "Import failed", "Import failed", "导入失败", "導入失敗");
    public static final Coder INVALID_REQUEST = new Coder("0084", "Invalid request", "Invalid request", "无效请求", "無效請求");
    public static final Coder UPLOAD_SIZE_OVER_LIMIT = new Coder("0085", "Upload size over limit {0}", "Upload size over limit {0}", "上传文件大小不能超过{0}", "上傳文件大小不能超過{0}");
    public final static Coder SOME_DATA_NOT_EXIST_OR_DELETED = new Coder("0086", "Some data not exist or has delete.", "Some data not exist or has delete.", "部分数据不存在或已删除", "部分數據不存在或已刪除");

    public static class Auth {
        public static final Coder ACCT_ERROR = new Coder("AU0001", "Mail or password is incorrect", "Mail or password is incorrect", "邮箱或密码错误", "郵箱或密碼錯誤");
        public static final Coder MAIL_ERROR = new Coder("AU0002", "Mail is incorrect", "Mail is incorrect", "邮箱错误", "郵箱錯誤");
        public static final Coder MAIL_NOT_EMPTY = new Coder("AU0003", "E-mail can not be empty", "E-mail can not be empty", "邮箱不能为空", "郵箱不能為空");
        public static final Coder PASSWORD_NOT_EMPTY = new Coder("AU0004", "password can not be blank", "password can not be blank", "密码不能为空", "密碼不能為空");
        public static final Coder PASSWORD_FORMAT_ERROR = new Coder("AU0005", "password format error", "password format error", "密码格式错误", "密碼格式錯誤");
        public static final Coder PASSWORD_ERROR = new Coder("AU0006", "wrong password", "wrong password", "密码错误", "密碼錯誤");
        public static final Coder PASSWORD_DIFF = new Coder("AU0007", "passwords entered twice are inconsistent", "passwords entered twice are inconsistent", "两次输入的密码不一致", "兩次輸入的密碼不一致");
        public static final Coder INV_MOBILE = new Coder("AU0008", "Invalid mobile number", "Invalid mobile number", "手机号码无效", "手機號碼無效");
        public static final Coder INV_TOKEN = new Coder("AU0009", "Invalid token", "Invalid token", "令牌无效", "令牌無效");
        public final static Coder TOKEN_EXPIRE = new Coder("AU0010", "The token has expired", "The token has expired", "令牌已过期", "令牌已過期");
        public static final Coder OTP_NOT_EMPTY = new Coder("AU0011", "otp can not be blank", "otp can not be blank", "验证码不能为空", "驗證碼不能為空");
        public final static Coder OTP_ERROR = new Coder("AU0012", "The otp is error", "The otp is error", "验证码错误", "驗證碼錯誤");
        public final static Coder INV_IP = new Coder("AU0013", "Invalid IP Address", "Invalid IP Address", "IP地址无效", "IP地址無效");
        public final static Coder IP_ERROR_RESET = new Coder("AU0014", "The IP address is incorrect, and the password cannot be forgotten", "The IP address is incorrect, and the password cannot be forgotten", "IP地址错误，无法进行忘记密码操作", "IP地址錯誤，無法進行忘記密碼操作");
        public final static Coder SMS_OVER_LIMIT = new Coder("AU0015", "Get captcha too often. Please try again later.", "Get captcha too often. Please try again later.", "获取验证码过于频繁，请稍后再试", "獲取驗證碼過於頻繁，請稍後再試");
        public static final Coder ACCT_LOGIN_FAILED = new Coder("AU0016", "Login failed, please confirm if you have permission", "Login failed, please confirm if you have permission", "登录失败，请确认是否有权限", "登錄失敗，請確認是否有權限");
        public final static Coder ANOTHER_LOGIN = new Coder("AU0017", "You have logged in elsewhere, please log in again", "You have logged in elsewhere, please log in again", "已在别处登录，请重新登录", "已在別處登錄，請重新登錄");
        public final static Coder SIGN_ERROR = new Coder("AU0018", "Signature Fail", "Signature Fail", "签名错误", "簽名錯誤");
        public final static Coder PLAN_UN_SPECIFIED = new Coder("AU0019", "Your plan unspecified", "Your plan unspecified", "您尚未配置相关计划", "您尚未配置相關計劃");
        public final static Coder PLAN_UN_ACTIVE = new Coder("AU0020", "Your plan is not active", "Your plan is not active", "您的计划尚未激活", "您的計劃尚未激活");
        public final static Coder AUTH_EXPIRE = new Coder("AU0021", "Identity information has expired", "Identity information has expired", "身份信息已过期", "身份信息已過期");
        public final static Coder AUTH_CODE_UN_SEND = new Coder("AU0023", "Please sending auth code first", "Please sending auth code first", "请先发送验证码", "請先發送驗證碼");
        public final static Coder NO_DATA_PERMISSION = new Coder("AU0022", "No data permission", "No data permission", "无数据权限", "無數據權限");
        public final static Coder REFRESH_TOKEN = new Coder("AU0024", "refresh token", "refresh token", "刷新令牌", "刷新令牌");
    }

    public static class Grant {
        public static final Coder NOT_FOUND = new Coder("GR0001", "Esop grant not found", "Esop grant not found", "找不到ESOP授予", "找不到ESOP授予");
        public static final Coder EXIST = new Coder("GR0002", "Grant number already exist in this plan", "Grant number already exist in this plan", "授予编号已存在", "授予編號已存在");
        public static final Coder CREATE_FAIL = new Coder("GR0003", "Create esop grant fail", "Create esop grant fail", "创建esop授权失败", "創建esop授權失敗");
        public static final Coder DELETE_FAIL = new Coder("GR0004", "Delete esop grant fail", "Delete esop grant fail", "删除esop授权失败", "刪除esop授權失敗");
        public static final Coder ACTIVE_FAIL = new Coder("GR0005", "Active esop grant fail", "Active esop grant fail", "启用esop授权失败", "啟用esop授權失敗");
        public static final Coder NO_VESTING = new Coder("GR0006", "At least one vesting is required", "At least one vesting is required", "至少需要一个归属", "至少需要一個歸屬");
        public static final Coder QUANTITY_UNEQUALS = new Coder("GR0007", "Total vesting unequals total grant", "Total vesting unequals total grant", "归属总数不等于授予数", "歸屬總數不等於授予數");
        public static final Coder JUDGMENT_DATE_FAIL = new Coder("GR0008", "Grant Number {0} party judgment date was before or equal today", "Grant Number {0}, party's judgment date was before or equal today", "授予编号{0}对应员工的判断日期少于或等于今日日期", "授予編號{0}對應員工的判斷日期少於或等於今日日期");


        public static final Coder ALREADY_DELETED = new Coder("GR0009", "Operation fail, please refresh and try again", "Operation fail, please refresh and try again", "操作失败，请刷新重试", "操作失敗，請刷新重試");
        public static final Coder ALREADY_ACTIVATED = new Coder("GR0010", "Operation fail, please refresh and try again", "Operation fail, please refresh and try again", "操作失败，请刷新重试", "操作失敗，請刷新重試");
        public static final Coder GET_CLOSEST_VESTING_DATE_FAIL = new Coder("GR0011", "get closest vesting date fail", "get closest vesting date fail", "获得最接近的归属日期失败", "獲得最接近的歸屬日期失敗");
        public static final Coder GET_CLOSEST_VESTING_QUANTITY_FAIL = new Coder("GR0012", "get closest vesting quantity fail", "get closest vesting quantity fail", "获取最接近的归属数量失败", "獲取最接近的歸屬數量失敗");

        public static final Coder GTANT_NUMBER_ERROR = new Coder("GR0013", "Grant number format error, please enter up to eleven digits", "Grant number format error, please enter up to eleven digits", "授予编号格式错误，请输入最多11位数字", "授予編號格式錯誤，請輸入最多11位數字");
        public static final Coder GTANT_NOT_EMPTY = new Coder("GR0014", "Grant number is required", "Grant number is required", "授予编号必填", "授予編號必填");
        public static final Coder NAME_NOT_EMPTY = new Coder("GR0015", "Name is required", "Name is required", "姓名必填", "姓名必填");
        public static final Coder GTANT_NAME_FROMAT_ERROR = new Coder("GR0016", "Incorrect name", "Incorrect name", "姓名格式错误", "姓名格式錯誤");
        public static final Coder JOBNUM_NOT_EMPTY = new Coder("GR0017", "Job number is required", "Job number is required", "工号必填", "工號必填");
        public static final Coder GRANT_DATE_NOT_EMPTY = new Coder("GR0018", "Grant date is required", "Grant date is required", "授予日期必填", "授予日期必填");
        public static final Coder GRANT_DATE_ERROR = new Coder("GR0019", "Grant date is incorrect, please re-enter", "Grant date is incorrect, please re-enter", "授予日期有误，请重新输入", "授予日期有誤，請重新輸入");
        public static final Coder GRANT_QTY_NOT_EMPTY = new Coder("GR0020", "Number of awards required", "Number of awards required", "授予数量必填", "授予數量必填");
        public static final Coder GRANT_QTY_ERROR = new Coder("GR0021", "Wrong number of awards, please re-enter", "Wrong number of awards, please re-enter", "授予数量有误，请重新输入", "授予數量有誤，請重新輸入");
        public static final Coder GRANT_PRICE_NOT_EMPTY = new Coder("GR0022", "The grant price is required", "The grant price is required", "授予价必填", "授予價必填");
        public static final Coder GRANT_PRICE_ERROR = new Coder("GR0023", "The grant price is incorrect, please re-enter", "The grant price is incorrect, please re-enter", "授予价有误，请重新输入", "授予價有誤，請重新輸入");
        public static final Coder PARTY_INFO_NOT_EXIST = new Coder("GR0024", "Party info not exist", "Party info not exist", "工号不存在", "工號不存在");
        public static final Coder GTANT_NUMBER_EXIST = new Coder("GR0025", "Grant number exist", "Grant number exist", "授予编号已存在", "授予編號已存在");
        public static final Coder GRANT_QTY_NOT_EQUAL_VESTING_QTY = new Coder("GR0026", "Grant quantity not equal vesting quantity", "Grant quantity not equal vesting quantity", "授予数量不等于既定数量", "授予數量不等於既定數量");
        public static final Coder TOTAL_GRANT_ZERO_OR_NULL = new Coder("GR0027", "Total grant equal to null or 0", "Total grant equal to null or 0", "授予总量为空或0", "授予總量為空或0");
        public static final Coder TOTAL_VESTED_OR_UNVESTED_ZERO_OR_NULL = new Coder("GR0028", "Total vested or total unvested equal to null or 0", "Total vested or total unvested equal to null or 0", "已归属或未归属的总数为空或0", "已歸屬或未歸屬的總數為空或0");
        public static final Coder FIND_UPLOAD_RSU_GRANT_TEMPLATE_FAIL = new Coder("GR0029", "Find upload trade grant template fail", "Can not found template file", "无法找到指定模板", "無法找到指定模板");
        public static final Coder VESTING_QTY_GRANTER_THAN_TOTAL_GRANT = new Coder("GR0030", "The total number of vestings must equal the number awarded", "The total number of vestings must equal the number awarded", "归属数量总和必须等于授予数量", "歸屬數量總和必須等於授予數量");
        public static final Coder VESTING_DATE_BEFORE_GRANT_DATE = new Coder("GR0031", "Vesting date is before grant date", "Vesting date is before grant date", "归属日期不能小于授予日期", "歸屬日期不能小於授予日期");
        public static final Coder GTANT_NUMBER_REPEAT = new Coder("GR0032", "Grant number {0} repeat", "Grant number {0} repeat", "文件中授予编号{0}重复", "文件中授予編號{0}重複");
        public static final Coder GTANT_NAME_ERROR = new Coder("GR0033", "job number does not match name", "job number does not match name", "工号与姓名不匹配", "工號與姓名不匹配");
        public static final Coder GRANT_RECORD_EMPTY = new Coder("GR0034", "Grant record empty", "Grant record empty", "授予记录数据不能为空", "授予記錄數據不能為空");
        public static final Coder CLOSING_PRICE_ERROR = new Coder("GR0035", "Closing price error", "Closing price error", "授予日收盘价有误，请重新输入", "授予日收盤價有誤，請重新輸入");

        public static final Coder GN_NAME_NOT_EMPTY = new Coder("GR0036", "Name is required", "Name is required", "姓名必填", "姓名必填");
        public static final Coder GN_JOBNUM_NOT_EMPTY = new Coder("GR0037", "Job number is required", "Job number is required", "工号必填", "工號必填");
        public static final Coder GN_GRANT_DATE_NOT_EMPTY = new Coder("GR0038", "Grant date is required", "Grant date is required", "授予日期必填", "授予日期必填");
        public static final Coder GN_GRANT_DATE_ERROR = new Coder("GR0039", "Grant date format error", "Grant date format error", "授予日期格式错误", "授予日期格式錯誤");
        public static final Coder GN_GRANT_PRICE_NOT_EMPTY = new Coder("GR0040", "The grant price is required", "The grant price is required", "授予价必填", "授予價必填");
        public static final Coder GN_GRANT_PRICE_ERROR = new Coder("GR0041", "Grant price error", "Grant price error", "授予价格式错误", "授予價格式錯誤");
        public static final Coder GN_GRANT_CLOSING_PRICE_ERROR = new Coder("GR0042", "Closing price error", "Closing price error", "授予日收盘价格式错误", "授予日收盤價格式錯誤");
        public static final Coder GN_GRANT_VESTING_DATE_NOT_EMPTY = new Coder("VT0043", "Attribution date is required", "Attribution date is required", "归属日期必填", "歸屬日期必填");
        public static final Coder GN_VESTING_DATE_ERROR = new Coder("VT0044", "Attribution date format error", "Attribution date format error", "归属日期格式错误", "歸屬日期格式錯誤");
        public static final Coder GN_VESTING_QUANTITY_NOT_EMPTY = new Coder("VT0045", "The number of attribution is required", "The number of attribution is required", "归属数量必填", "歸屬數量必填");
        public static final Coder GN_VESTING_QUANTITY_ERROR = new Coder("VT0046", "Attribution quantity format error", "Attribution quantity format error", "归属数量格式错误", "歸屬數量格式錯誤");
        public static final Coder GN_GTANT_NUMBER_REPEAT = new Coder("GR0047", "Grant number repeat", "Grant number repeat", "授予编号重复", "授予編號重複");
        public static final Coder GN_PARTY_INFO_NOT_EXIST = new Coder("GR0048", "Job number does not exist", "Job number does not exist", "工号不存在", "工號不存在");
        public static final Coder GN_VESTING_QTY_GRANTER_THAN_TOTAL_GRANT = new Coder("GR0049", "The total number of vestings must equal the number awarded", "The total number of vestings must equal the number awarded", "归属数量总和必须等于授予数量", "歸屬數量總和必須等於授予數量");
        public static final Coder GN_VESTING_DATE_BEFORE_GRANT_DATE = new Coder("GR0050", "Vesting date is before grant date", "Vesting date is before grant date", "归属日期不能小于授予日期", "歸屬日期不能小於授予日期");
        public static final Coder GN_GRANT_QTY_NOT_EMPTY = new Coder("GR0051", "Number of awards required", "Number of awards required", "授予数量必填", "授予數量必填");
        public static final Coder GN_GRANT_QTY_ERROR = new Coder("GR0052", "Grant quantity is malformed", "Grant quantity is malformed", "授予数量格式错误", "授予數量格式錯誤");
        public static final Coder GN_RULE = new Coder("GR0053", "Grant records and attribution rules are inconsistent in grant numbers", "Grant records and attribution rules are inconsistent in grant numbers", "授予记录、归属规则两个子表的授予编号不一致", "授予記錄、歸屬規則兩個子表的授予編號不一致");
        public static final Coder GN_VESTING_DATE_REPEAT = new Coder("GR0054", "Grant vesting date repeat", "Grant vesting date repeatt", "归属日期重复", "歸屬日期重複");
        public static final Coder GN_EXERCISE_PRICE_NOT_EMPTY = new Coder("GR0055", "The exercise price is required", "The exercise price is required", "行权价必填", "行權價必填");
        public static final Coder GN_EXERCISE_PRICE_ERROR = new Coder("GR0056", "Exercise price error", "Exercise price error", "行权价格式錯誤", "行權價格式錯誤");
        public static final Coder GN_EXPIRATION_DATE_LESS_THAN_VEST = new Coder("GR0057", "The expiration date must be greater than the attribution date", "The expiration date must be greater than the attribution date", "失效日期必须大于归属日期", "失效日期必須大於歸屬日期");
        public static final Coder GN_EXPIRATION_DATE_ERROR = new Coder("GR0058", "Expiration date format error", "Expiration date format error", "失效日期格式错误", "失效日期格式錯誤");
        public static final Coder GN_CLOSING_PRICE_NOT_EMPTY = new Coder("GR0059", "Closing price required", "Closing price required", "授予日收盘价必填", "授予日收盤價必填");
    }

    public static class Vesting {
        public static final Coder VESTING_NOT_FOUND = new Coder("VT0001", "Esop vesting not found", "Esop vesting not found", "找不到Esop归属", "找不到Esop歸屬");
        public static final Coder VESTING_EXIST = new Coder("VT0002", "Esop vesting already exist", "Esop vesting already exist", "Esop归属已存在", "Esop歸屬已存在");
        public static final Coder CREATE_FAIL = new Coder("VT0003", "Create esop vesting fail", "Create esop vesting fail", "创建Esop归属失败", "創建Esop歸屬失敗");
        public static final Coder ALREADY_APPROVED = new Coder("VT0004", "Operation fail, please refresh and try again", "Operation fail, please refresh and try again", "操作失败，请刷新重试", "操作失敗，請刷新重試");
        public static final Coder APPROVE_FAIL = new Coder("VT0005", "Approve fail", "Approve fail", "开启失败", "開啟失敗");
        public static final Coder SUSPEND_FAIL = new Coder("VT0006", "Suspend fail", "Suspend fail", "暂停失败", "暫停失敗");
        public static final Coder ALREADY_SUSPEND = new Coder("VT0007", "This vesting is already suspend", "This vesting is already suspend", "审核状态已经为暂缓审核，不能重复操作", "審核狀態已經為暫緩審核，不能重複操作");
        //        public static final Coder PARTIALLY_ALREADY_SUSPEND = new Coder("200", "This vesting is already suspend", "This vesting is already suspend", "对于审核状态已经为暂缓审核的RSU归属，无法进行暂缓审核操作", "對於審核狀態已經為暫緩審核的RSU歸屬，無法進行暫緩審核操作");
        public static final Coder UPLOAD_VESTING_EMPTY = new Coder("VT0008", "Upload vesting empty", "Upload vesting empty", "导入归属规则不能为空", "導入歸屬規則不能為空");
        public static final Coder VESTING_DATE_NOT_EMPTY = new Coder("VT0009", "Attribution date is required", "Attribution date is required", "归属日期必填", "歸屬日期必填");
        public static final Coder VESTING_DATE_ERROR = new Coder("VT0010", "Attribution date is wrong, please re-enter", "Attribution date is wrong, please re-enter", "归属日期有误，请重新输入", "歸屬日期有誤，請重新輸入");
        public static final Coder VESTING_QUANTITY_NOT_EMPTY = new Coder("VT0011", "The number of attribution is required", "The number of attribution is required", "归属数量必填", "歸屬數量必填");
        public static final Coder VESTING_QUANTITY_ERROR = new Coder("VT0012", "The number of attribution is incorrect, please re-enter", "The number of attribution is incorrect, please re-enter", "归属数量有误，请重新输入", "歸屬數量有誤，請重新輸入");
        public static final Coder VESTING_RECORD_EMPTY = new Coder("VT0013", "Vesting record empty", "Vesting record empty", "归属规则数据不能为空", "歸屬規則數據不能為空");
        public static final Coder EFFECTIVE_SUCCESS = new Coder("VT0014", "Effective", "Vesting record empty", "生效成功", "生效成功");
        public static final Coder DATA_DEFECTIVE_SUCCESS = new Coder("VT0015", "{0}data effective", "{0}data effective", "{0}条数据生效成功", "{0}條數據生效成功");
        public static final Coder REVIEW_SUCCESS = new Coder("VT0016", "Successful review", "Successful review", "审核通过成功", "審核通過成功");
        public static final Coder DATA_REVIEW_SUCCESS = new Coder("VT0017", "{0}data successful review", "{0}data successful review", "{0}条数据审核成功", "{0}條數據審核成功");
        public static final Coder SUSPENSION_REVIEW_SUCCESS = new Coder("VT0018", "Suspension of successful audit.For the RSU attribution whose audit status has been deferred, the deferred audit operation cannot be performed", "Suspension of successful audit.For the RSU attribution whose audit status has been deferred, the deferred audit operation cannot be performed", "暂缓审核成功，对于审核状态已经为暂缓审核的RSU归属，无法进行暂缓审核操作", "暫緩審核成功，對於審核狀態已經為暫緩審核的RSU歸屬，無法進行暫緩審核操作");
        public static final Coder DATA_SUSPENSION_REVIEW_SUCCESS = new Coder("VT0019", "{0}data suspension of successful audit.For the RSU attribution whose audit status has been deferred, the deferred audit operation cannot be performed", "{0}data suspension of successful audit.For the RSU attribution whose audit status has been deferred, the deferred audit operation cannot be performed", "{0}条数据暂缓审核成功，对于审核状态已经为暂缓审核的RSU归属，无法进行暂缓审核操作", "{0}條數據暫緩審核成功，對於審核狀態已經為暫緩審核的RSU歸屬，無法進行暫緩審核操作");
        public static final Coder DOWNLOAD_SUCCESS = new Coder("VT0020", "Download successful", "download successful", "下载成功", "下載成功");
        public static final Coder EXPIRATION_DATE_MUST_GREATER_VESTING_DATE = new Coder("VT0021", "The expiration date must be greater than the vesting date", "The expiration date must be greater than the vesting date", "失效日期必须大于归属日期", "失效日期必須大於歸屬日期");
        public static final Coder RULE_NAME_EXISTS = new Coder("VT0022", "Rule name already exists", "Rule name already exists", "规则名称已存在", "規則名稱已存在");
    }

    public static class Party {
        public static final Coder PARTY_NAME_NOT_EMPTY = new Coder("PT001", "Participant id,Employee name is required", "Participant id,Employee name is required", "姓名必填", "姓名必填");
        public static final Coder PARTY_NAME_ERROR = new Coder("PT002", "Participant id,Please enter Chinese or English name, Chinese does not accept spaces", "Participant id,Please enter Chinese or English name, Chinese does not accept spaces", "姓名格式错误，请输入中文或英文名字，中文不接受空格，最多50位", "姓名格式錯誤，請輸入中文或英文名字，中文不接受空格，最多50位");
        public static final Coder PARTICIPANTID_NOT_EMPTY = new Coder("PT003", "Job number is required", "Job number is required", "工号必填", "工號必填");
        public static final Coder PARTICIPANTID_ERROR = new Coder("PT004", "Work number, please enter numbers, letters, up to 50 digits in length", "Work number, please enter numbers, letters, up to 50 digits in length", "工号格式错误，请输入数字、字母，长度最多50位", "工號格式錯誤，請輸入數字，字母，長度最多50位");
        public static final Coder PARTICIPANTID_EXIST = new Coder("PT005", "Participant id has exist", "Participant id has exist", "工号已存在", "工號已存在");
        public static final Coder PARTICIPANTID_FORMAT_INCORRECT = new Coder("PT006", "Participant id format is incorrect", "Participant id format is incorrect", "工号格式不正确", "工號格式不正確");
        public static final Coder MOBILE_NOT_EMPTY = new Coder("PT007", "Mobile phone number is required", "Mobile phone number is required", "手机号必填", "手機號必填");
        public static final Coder MOBILE_ERROR = new Coder("PT008", "Phone number is wrong, please re-enter", "Phone number is wrong, please re-enter", "手机号格式错误", "手機號格式錯誤");
        public static final Coder MOBILE_EXIST = new Coder("PT009", "Mobile has exist", "Mobile has exist", "手机号已存在", "手機號已存在");
        public static final Coder LANGUAGE_NOT_EMPTY = new Coder("PT010", "Common language required", "Common language required", "常用语言必选", "常用語言必選");
        public static final Coder LANGUAGE_ERROR = new Coder("PT011", "Common language is wrong, please choose", "Common language is wrong, please choose", "常用语言错误", "常用語言錯誤");
        public static final Coder IDTYPE_NOT_EMPTY = new Coder("PT012", "Document type required", "Document type required", "证件类型必选", "證件類型必選");
        public static final Coder IDTYPE_ERROR = new Coder("PT013", "Bad document type, please choose", "Bad document type, please choose", "证件类型错误", "證件類型錯誤");
        public static final Coder IDNUMBER_NOT_EMPTY = new Coder("PT014", "Bad ID number, please re-enter", "Bad ID number, please re-enter", "证件号必填", "證件號必填");
        public static final Coder IDNUMBER_ERROR = new Coder("PT015", "Bad ID number, please re-enter", "Bad ID number, please re-enter", "证件号请输入数字，字母", "證件號請輸入數字，字母");
        public static final Coder ADDRESS_NOT_EMPTY = new Coder("PT016", "Correspondence address is required", "Correspondence address is required", "通讯地址必填", "通訊地址必填");
        public static final Coder ADDRESS_ERROR = new Coder("PT017", "Correspondence address is wrong", "Correspondence address is wrong", "通讯地址错误", "通訊地址錯誤");
        public static final Coder BEHALF_TAX_CALC_UN_SPECIFIED = new Coder("PT018", "Behalf tax calculation is not specified", "Behalf tax calculation is not specified", "代算税款必选", "代算稅款必選");
        public static final Coder BEHALF_TAX_ERROR = new Coder("PT019", "Behalf tax calculation is wrong", "Behalf tax calculation is wrong", "代算税款错误", "代算稅款錯誤");
        public static final Coder BEHALF_TAX_WH_UN_SPECIFIED = new Coder("PT020", "Behalf tax withholding is not specified", "Behalf tax withholding is not specified", "代扣稅款必选", "代扣稅款必選");
        public static final Coder BEHALF_TAX_WH_ERROR = new Coder("PT021", "Behalf tax withholding is wrong", "Behalf tax withholding is wrong", "代扣稅款错误", "代扣稅款錯誤");
        public static final Coder BEHALF_COST_WH_UN_SPECIFIED = new Coder("PT022", "Behalf cost withholding is not specified", "Behalf cost withholding is not specified", "代扣成本必选", "代扣成本必選");
        public static final Coder BEHALF_COST_ERROR = new Coder("PT023", "Behalf cost withholding is wrong", "Behalf cost withholding is wrong", "代扣成本错误", "代扣成本錯誤");
        public static final Coder UNIFIED_SETTLEMENT_UN_SPECIFIED = new Coder("PT024", "Unified settlement is not specified", "Unified settlement is not specified", "统一结汇必选", "統一結匯必選");
        public static final Coder UNIFIED_SETTLEMENT_ERROR = new Coder("PT025", "Unified settlement is wrong", "Unified settlement is wrong", "统一结汇错误", "統一結匯錯誤");
        public static final Coder AFFILIATED_PERSON_UN_SPECIFIED = new Coder("PT026", "Affiliated person is not specified", "Affiliated person is not specified", "关联人士必选", "關聯人士必選");
        public static final Coder AFFILIATED_PERSON_ERROR = new Coder("PT027", "Affiliated person is wrong", "Affiliated person is wrong", "关联人士错误", "關聯人士錯誤");
        public static final Coder SIGNATURE_AGREEMENT_UN_SPECIFIED = new Coder("PT028", "Signature agreement is not specified", "Signature agreement is not specified", "签署协议必选", "簽署協議必選");
        public static final Coder SIGNATURE_AGREEMENT_ERROR = new Coder("PT029", "Signature agreement is wrong", "Signature agreement is wrong", "签署协议错误", "簽署協議錯誤");

        public static final Coder TEMPLATE_ERROR = new Coder("PT030", "Template error", "Template error", "模板错误", "模板錯誤");
        public static final Coder DEPARTMENT_NOT_EMPTY = new Coder("PT032", "Department is not empty", "Department is not empty", "部门不能为空", "部門不能為空");
        public static final Coder DEPARTMENT_ERROR = new Coder("PT033", "Department error", "Department error", "部门错误", "部門錯誤");
        public static final Coder PARTY_NOT_FOUND = new Coder("PT034", "Party not exist", "Party not exist", "员工不存在", "員工不存在");
        public static final Coder JUDGE_DATE_ERROR = new Coder("PT035", "Judgment date is wrong, please re-enter", "Judgment date is wrong, please re-enter", "判断日期格式错误", "判斷日期格式錯誤");
        public static final Coder GRANT_EXIST = new Coder("PT036", "Party has grant", "Party has grant", "该员工已经有授予记录，不能删除", "該員工已經有授予記錄，不能刪除");
        public static final Coder ALL_GRANT = new Coder("PT037", "All party has grant", "All party has grant", "选中员工已经有授予记录，不能删除", "選中員工已經有授予記錄，不能刪除");
        public static final Coder NOT_AFFILIATED_PERSON = new Coder("PT038", "Not Affiliated person", "Not Affiliated person", "没有关联人", "沒有關聯人");
        public static final Coder FILE_FORMAT_ERROR = new Coder("PT039", "File format error", "File format error", "文件格式错误", "文件格式錯誤");
        public static final Coder UPDATE_PARTY_FAIL = new Coder("PT040", "Update party fail", "Update party fail", "更新员工信息失败", "更新員工信息失敗");
        public static final Coder HAS_GRANT_EXIST = new Coder("PT041", "Exist Party has grant", "Exist Party has grant", "有员工已经有授予记录，不能删除", "有員工已經有授予記錄，不能刪除");
        public static final Coder DATA_HAS_GRANT_EXIST = new Coder("PT042", "{0} data successfully deleted.Exist Party has grant", "{0} data successfully deleted.Exist Party has grant", "{0}条数据删除成功，有员工已经有授予记录，不能删除", "{0}條數據刪除成功，有員工已經有授予記錄，不能刪除");
        public static final Coder MOBILE_NUMBER_NOT_CORRESPOND = new Coder("PT043", "The job number already exists, phone number is not allowed to be modified", "The job number already exists, phone number is not allowed to be modified", "该工号已存在，对应的手机号不允许修改", "該工號已存在，對應的手機號不允許修改");
        public static final Coder PARTICIPANTID_NOT_CORRESPOND = new Coder("PT044", "The mobile phone number already exists, work number is not allowed to be modified", "The mobile phone number already exists, work number is not allowed to be modified", "该手机号已存在，对应的工号不允许修改", "該手機號已存在，對應的工號不允許修改");
        public static final Coder UPLOAD_DATA_EMPTY = new Coder("PT045", "upload data empty", "upload data empty", "导入数据不能为空", "導入數據不能為空");
        public static final Coder DIALING_NOT_EMPTY = new Coder("PT046", "Dialing code required", "Dialing code required", "国际电话区号必填", "國際電話區號必填");
        public static final Coder DIALING_CODE_ERROR = new Coder("PT047", "Wrong dialing code", "Wrong dialing code", "国际电话区号有误", "國際電話區號有誤");
        public static final Coder PARTICIPANTID_REPEAT = new Coder("PT048", "job number {0} repeat", "job number {0} repeat", "工号{0}重复", "工號{0}重複");
        public static final Coder MOBILE_NUMBER_REPEAT = new Coder("PT049", "mobile number {0} repeat", "mobile number {0} repeat", "手机号{0}重复", "手機號{0}重複");
        public static final Coder WECHAT_ERROR = new Coder("PT050", "Incorrect wechat number format", "Incorrect wechat number format", "微信号码格式有误", "微信號碼格式有誤");
        public static final Coder GENDER_ERROR = new Coder("PT051", "Wrong gender, please select", "Wrong gender, please select", "性别错误", "性別錯誤");
        public static final Coder COUNTRY_REGION_ERROR = new Coder("PT052", "Country is wrong, please select", "Country is wrong, please select", "国家/地区错误", "國家/地區錯誤");
        public static final Coder PARTY_RECORD_EMPTY = new Coder("PT053", "Party record empty", "Party record empty", "员工数据不能为空", "員工數據不能為空");
        public static final Coder AFFIALIATED_RECORD_EMPTY = new Coder("PT054", "Affialiated person record empty", "Affialiated person record empty", "关联人士数据不能为空", "關聯人士數據不能為空");

        public static final Coder IMPORT_SUCCESS = new Coder("PT055", "Import succeeded", "Import succeeded", "导入成功", "導入成功");
        public static final Coder MODIFIED_SUCCESS = new Coder("PT056", "Successfully modified", "Successfully modified", "修改成功", "修改成功");
        public static final Coder DELETED_SUCCESS = new Coder("PT057", "successfully deleted", "successfully deleted", "删除成功", "刪除成功");
        public static final Coder DATA_DELETED_SUCCESS = new Coder("PT058", "{0} data successfully deleted", "{0} data successfully deleted", "{0}条数据删除成功", "{0}條數據刪除成功");
        public static final Coder JOB_TITLE_ERROR = new Coder("PT059", "Job title error", "Job title error", "职务错误，请重新输入", "職務錯誤，請重新輸入");

        public static final Coder PARTY_DIALING_NOT_EMPTY = new Coder("PT060", "Dialing code required", "Dialing code required", "国际电话区号必填", "國際電話區號必填");
        public static final Coder PARTY_DIALING_CODE_ERROR = new Coder("PT061", "Wrong dialing code", "Wrong dialing code", "国际电话区号错误", "國際電話區號錯誤");
        public static final Coder PARTY_MOBILE_NOT_EMPTY = new Coder("PT062", "Mobile phone number is required", "Mobile phone number is required", "手机号必填", "手機號必填");
        public static final Coder PARTY_MOBILE_ERROR = new Coder("PT063", "Phone number is wrong, please re-enter", "Phone number is wrong, please re-enter", "手机号格式错误", "手機號格式錯誤");
        public static final Coder PARTY_NAME_NOT_EMPTY_2 = new Coder("PT064", "Employee name is required", "Employee name is required", "员工姓名必填", "員工姓名必填");
        public static final Coder PARTY_NAME_ERROR_2 = new Coder("PT065", "Please enter Chinese or English name, Chinese does not accept spaces，Up to 50 digits in length", "Please enter Chinese or English name, Chinese does not accept spaces，Up to 50 digits in length", "姓名格式错误，员工姓名请输入中文或英文名字，中文不接受空格，最多50位", "姓名格式錯誤，員工姓名請輸入中文或英文名字，中文不接受空格，最多50位");
        public static final Coder PARTY_GENDER_ERROR = new Coder("PT066", "Wrong gender, please select", "Wrong gender, please select", "性别错误", "性別錯誤");
        public static final Coder PARTY_DEPARTMENT_ERROR = new Coder("PT067", "Department error，Up to 50 digits in length", "Department error，Up to 50 digits in length", "部门错误，长度最多50位", "部門錯誤，長度最多50位");
        public static final Coder PARTY_JOB_TITLE_ERROR = new Coder("PT068", "Job title error，Up to 50 digits in length", "Job title error，Up to 50 digits in length", "职务错误，长度最多50位", "職務錯誤，長度最多50位");
        public static final Coder PARTY_WECHAT_ERROR = new Coder("PT069", "The micro signal code format is wrong, please enter numbers and letters, the maximum length is 50 digits", "The micro signal code format is wrong, please enter numbers and letters, the maximum length is 50 digits", "微信号格式错误，请输入数字、字母、下划线或减号，以字母开头，长度最多20位", "微信號格式錯誤,請輸入數字、字母，下劃線或減號，以字母開頭，長度最多20位");
        public static final Coder PARTY_COUNTRY_REGION_ERROR = new Coder("PT070", "Country is wrong, please select", "Country is wrong, please select", "国家/地区错误", "國家/地區錯誤");
        public static final Coder PARTY_LANGUAGE_NOT_EMPTY = new Coder("PT071", "Common language required", "Common language required", "常用语言必填", "常用語言必填");
        public static final Coder PARTY_LANGUAGE_ERROR = new Coder("PT072", "Common language is wrong, please choose", "Common language is wrong, please choose", "常用语言错误", "常用語言錯誤");
        public static final Coder PARTY_IDTYPE_NOT_EMPTY = new Coder("PT073", "Document type required", "Document type required", "证件类型必填", "證件類型必填");
        public static final Coder PARTY_IDTYPE_ERROR = new Coder("PT074", "Bad document type, please choose", "Bad document type, please choose", "证件类型错误", "證件類型錯誤");
        public static final Coder PARTY_IDNUMBER_NOT_EMPTY = new Coder("PT075", "Bad ID number, please re-enter", "Bad ID number, please re-enter", "证件号必填", "證件號必填");
        public static final Coder PARTY_IDNUMBER_ERROR = new Coder("PT076", "Bad ID number, please re-enter，Up to 50 digits in length", "Bad ID number, please re-enter，Up to 50 digits in length", "证件号格式错误，请输入数字、字母，长度最多50位", "證件號格式錯誤，請輸入數字，字母，長度最多50位");
        public static final Coder PARTY_ADDRESS_NOT_EMPTY = new Coder("PT077", "Correspondence address is required", "Correspondence address is required", "通讯地址必填", "通訊地址必填");
        public static final Coder PARTY_ADDRESS_ERROR = new Coder("PT078", "Correspondence address is wrong，Up to 50 digits in length", "Correspondence address is wrong，Up to 50 digits in length", "通讯地址错误，长度最多50位", "通訊地址錯誤，長度最多50位");
        public static final Coder PARTY_BEHALF_TAX_CALC_UN_SPECIFIED = new Coder("PT079", "Behalf tax calculation is not specified", "Behalf tax calculation is not specified", "是否代算税款必选", "是否代算稅款必選");
        public static final Coder PARTY_BEHALF_TAX_ERROR = new Coder("PT080", "Behalf tax calculation is wrong", "Behalf tax calculation is wrong", "是否代算税款错误", "是否代算稅款錯誤");
        public static final Coder PARTY_BEHALF_TAX_WH_UN_SPECIFIED = new Coder("PT081", "Behalf tax withholding is not specified", "Behalf tax withholding is not specified", "是否代扣税款必选", "是否代扣稅款必選");
        public static final Coder PARTY_BEHALF_TAX_WH_ERROR = new Coder("PT082", "Behalf tax withholding is wrong", "Behalf tax withholding is wrong", "是否代扣稅款错误", "是否代扣稅款錯誤");
        public static final Coder PARTY_BEHALF_COST_WH_UN_SPECIFIED = new Coder("PT083", "Behalf cost withholding is not specified", "Behalf cost withholding is not specified", "是否代扣成本必选", "是否代扣成本必選");
        public static final Coder PARTY_BEHALF_COST_ERROR = new Coder("PT084", "Behalf cost withholding is wrong", "Behalf cost withholding is wrong", "是否代扣成本错误", "是否代扣成本錯誤");
        public static final Coder PARTY_UNIFIED_SETTLEMENT_UN_SPECIFIED = new Coder("PT085", "Unified settlement is not specified", "Unified settlement is not specified", "是否统一结汇必选", "是否統一結匯必選");
        public static final Coder PARTY_UNIFIED_SETTLEMENT_ERROR = new Coder("PT086", "Unified settlement is wrong", "Unified settlement is wrong", "是否统一结汇错误", "是否統一結匯錯誤");
        public static final Coder PARTY_AFFILIATED_PERSON_UN_SPECIFIED = new Coder("PT087", "Affiliated person is not specified", "Affiliated person is not specified", "是否关联人士必选", "是否關聯人士必選");
        public static final Coder PARTY_AFFILIATED_PERSON_ERROR = new Coder("PT088", "Affiliated person is wrong", "Affiliated person is wrong", "是否关联人士错误", "是否關聯人士錯誤");
        public static final Coder PARTY_SIGNATURE_AGREEMENT_UN_SPECIFIED = new Coder("PT089", "Signature agreement is not specified", "Signature agreement is not specified", "是否签署协议必选", "是否簽署協議必選");
        public static final Coder PARTY_SIGNATURE_AGREEMENT_ERROR = new Coder("PT090", "Signature agreement is wrong", "Signature agreement is wrong", "是否签署协议错误", "是否簽署協議錯誤");
        public static final Coder PARTY_PARTICIPANTID_REPEAT = new Coder("PT091", "Duplicate job number in the file", "Duplicate job number in the file", "工号重复", "工號重複");
        public static final Coder PARTY_MOBILE_NUMBER_REPEAT = new Coder("PT092", "Phone number in the file is duplicated", "Phone number in the file is duplicated", "手机号重复", "手機號重複");
        public static final Coder GRANT_PARTICIPANTID_ERROR = new Coder("PT093", "Work number, please enter numbers, letters, up to 50 digits in length", "Work number, please enter numbers, letters, up to 50 digits in length", "工号格式错误", "工號格式錯誤");
        public static final Coder TAX_WITHHOLDING_ERROR = new Coder("PT094", "Whether the tax withholding is wrong, if the tax is not calculated, the tax cannot be withheld", "Whether the tax withholding is wrong, if the tax is not calculated, the tax cannot be withheld", "是否代扣税款错误，若不代算税款，则不能代扣税款", "是否代扣稅款錯誤，若不代算稅款，則不能代扣稅款");
        public static final Coder JUDGMENT_DATE_EXISTS = new Coder("PT095", "Judgment date already exists, please refresh and try again", "Judgment date already exists, please refresh and try again", "判断日期已存在，请刷新重试", "判斷日期已存在，請刷新重試");
        public static final Coder ID_NUMBER_EXISTS = new Coder("PT096", "ID number already exists", "ID number already exists", "证件号已存在", "證件號已存在");
        public static final Coder PARTICIPANTID_NOT_MATCH_NAME = new Coder("PT097", "The job number already exists, and the corresponding name is not allowed to be modified", "The job number already exists, and the corresponding name is not allowed to be modified", "该工号已存在，对应的姓名不允许修改", "該工號已存在，對應的姓名不允許修改");
        public static final Coder PARTICIPANTID_NOT_MATCH_ID_TYPE = new Coder("PT098", "The job number already exists, phone number is not allowed to be modified", "The job number already exists, and the corresponding certificate type cannot be modified", "该工号已存在，对应的证件类型不允许修改", "該工號已存在，對應的證件類型不允許修改");
        public static final Coder PARTICIPANTID_NOT_MATCH_ID_NUMBER = new Coder("PT99", "The job number already exists, and the corresponding ID number cannot be modified", "The job number already exists, and the corresponding ID number cannot be modified", "该工号已存在，对应的证件号不允许修改", "該工號已存在，對應的證件號不允許修改");
        public static final Coder ID_NUMBER_NOT_MATCH_PARTICIPANTID = new Coder("PT100", "The mobile phone number already exists, and the corresponding ID number cannot be modified", "The mobile phone number already exists, and the corresponding ID number cannot be modified", "该证件号已存在，对应的工号不允许修改", "該證件號已存在，對應的工號不允許修改");
        public static final Coder ID_NUMBER_REPEAT = new Coder("PT101", "Duplicate ID number", "Duplicate ID number", "证件号重复", "證件號重複");
    }

    public static class Exercise {
        public static final Coder START_TIME_NOT_NULL = new Coder("EX001", "Start date cannot be empty", "Start date cannot be empty", "开始日期不能为空", "開始日期不能爲空");
        public static final Coder END_TIME_NOT_NULL = new Coder("EX002", "End date cannot be empty", "End date cannot be empty", "结束日期不能为空", "開始日期不能爲空");
        public static final Coder DATE_ERROR = new Coder("EX003", "End date cannot be less than start date", "End date cannot be less than start date", "结束日期不能小于开始日期", "結束日期不能小於開始日期");
        public static final Coder COMPANY_ID_ERROR = new Coder("EX004", "Company id cannot be empty", "Company id cannot be empty", "公司id不能为空", "公司id不能爲空");
        public static final Coder STAFF_NULL_ERROR = new Coder("EX005", "Staff required", "Staff required", "员工必填", "員工必填");
        public static final Coder GRANT_NULL_ERROR = new Coder("EX006", "Grant number is required", "Grant number is required", "授予编号必填", "授予編號必填");
        public static final Coder PERIOD_SIZE_ERROR = new Coder("EX007", "Up to 10 date segments can be added", "Up to 10 date segments can be added", "最多可添加10个日期段", "最多可添加10個日期段");
        public static final Coder TIME_EXIST_ERROR = new Coder("EX008", "This time period already exists", "This time period already exists", "该时间段已存在", "該時間段已存在");
        public static final Coder WINDOW_SAVE_ERROR = new Coder("EX009", "Time period saving failed", "Time period saving failed", "时间段保存失败", "時間段保存失敗");
        public static final Coder PARAMS_ERROR = new Coder("EX010", "params error", "params error", "参数有误", "參數有誤");
        public static final Coder UPPER_LIMIT_ERROR = new Coder("EX011", "Upper limit cannot be less than 0", "Upper limit cannot be less than 0", "上限值不能小于0", "上限值不能小於0");
        public static final Coder EXISTS_EMPLOYESS_ERROR = new Coder("EX012", "is exists,You can't add it repeatedly.", "is exists,You can't add it repeatedly.", "已经设置了每日行权上限，不能重复添加", "已經設置了每日行權上限，不能重複添加");
        public static final Coder EXCEED_MAXIMUM_ERROR = new Coder("EX013", "A maximum of 10 time periods can be set", "A maximum of 10 time periods can be set", "最多只能设置10个时间段", "最多只能設置10個時間段");
        public static final Coder START_TIME_ERROR = new Coder("EX014", "Start time is required", "Start time is required", "开始时间必选", "開始時間必選");
        public static final Coder END_TIME_ERROR = new Coder("EX015", "End time is required", "Emd time is required", "结束时间必选", "結束時間必選");
        public static final Coder TIME_SELECTED_ERROR = new Coder("EX016", "The end time must be greater than the start time", "The end time must be greater than the start time", "结束时间必须大于开始时间", "結束時間大於開始時間");
        public static final Coder TIME_EXISTS_ERROR = new Coder("EX017", "End time is required", "Emd time is required", "时间段有重複", "結束時間大於開始時間");
        public static final Coder TIME_FORMAT_ERROR = new Coder("EX018", "Time format error, please enter HH:mm", "Time format error, please enter HH:mm", "时间格式有誤，請輸入HH:mm", "时间格式有誤，請輸入HH:mm");
        public static final Coder TIME_REPEATS_ERROR = new Coder("EX019", "No repetition of the time period", "No repetition of the time period", "时间段不能有重复", "時間段不能有重複");
        public static final Coder STAFF_REPEAR_LOCK_PERIOD_ERROR = new Coder("EX020", "The lock period of this employee already exists and cannot be added repeatedly: employee:{0},lock-in period:{1}", "The lock period of this employee already exists and cannot be added repeatedly: Employee:{0},Lock-in period:{1}", "该员工锁定期已存在，不能重复添加：员工：{0}，锁定期：{1}", "該員工鎖定期已存在，不能重複添加：員工：{0}，鎖定期：{1}");
        public static final Coder STAFF_REPEAR_WINDOW_PERIOD_ERROR = new Coder("EX021", "The employee window period already exists and cannot be added repeatedly: employee:{0}, window period:{1}", "The employee window period already exists and cannot be added repeatedly: employee:{0}, window period:{1}", "该员工窗口期已存在，不能重复添加：员工：{0}，窗口期：{1}", "該員工窗口期已存在，不能重複添加：員工：{0}，窗口期：{1}");
        public static final Coder GRANT_REPEAR_LOCK_PERIOD_ERROR = new Coder("EX022", "The grant number lock-up period already exists and cannot be added repeatedly: grant number: {0}, lock-up period: {1}", "The grant number lock-up period already exists and cannot be added repeatedly: grant number: {0}, lock-up period: {1}", "该授予编号锁定期已存在，不能重复添加：授予编号：{0}，锁定期：{1}", "該授予編號鎖定期已存在，不能重複添加：授予編號：{0}，鎖定期：{1}");
        public static final Coder GRANT_REPEAR_WINDOW_PERIOD_ERROR = new Coder("EX023", "The grant number window period already exists and cannot be added repeatedly: grant number: {0}, window period: {1}", "The grant number window period already exists and cannot be added repeatedly: grant number: {0}, window period: {1}", "该授予编号窗口期已存在，不能重复添加：授予编号：{0}，窗口期：{1}", "該授予編號窗口期已存在，不能重複添加：授予編號：{0}，窗口期：{1}");
        public static final Coder GET_STAFF_LOCK_PERIOD_ERROR = new Coder("EX024", "Failed to obtain the employee lock period", "Failed to obtain the employee lock period", "获取员工锁定期失败", "獲取員工鎖定期失敗");
        public static final Coder GET_STAFF_WINDOW_PERIOD_ERROR = new Coder("EX025", "Failed to get employee window period", "Failed to get employee window period", "获取员工窗口期失败", "獲取員工窗口期失敗");
        public static final Coder GET_GRANT_LOCK_PERIOD_ERROR = new Coder("EX026", "Failed to obtain grant number window", "Failed to obtain grant number window", "获取授予编号窗口期失败", "獲取授予編號窗口期失敗");
        public static final Coder GET_GRANT_WINDOW_PERIOD_ERROR = new Coder("EX027", "Failed to obtain grant number window", "Failed to obtain grant number window", "获取授予编号窗口期失败", "獲取授予編號窗口期失敗");
        public static final Coder SAVE_LOCK_PERIOD_ERROR = new Coder("EX028", "The lock period information has been changed, please refresh the page and try again", "The lock period information has been changed, please refresh the page and try again", "锁定期信息已更改，请刷新页面重试", "鎖定期信息已更改，請刷新頁面重試");
        public static final Coder SAVE_WINDOW_PERIOD_ERROR = new Coder("EX029", "The window information has been changed, please refresh the page and try again", "The window information has been changed, please refresh the page and try again", "窗口期信息已更改，请刷新页面重试", "窗口期信息已更改，請刷新頁面重試");
        public static final Coder OVER_COMPANY_UPPER_LIMIT = new Coder("EX030", "Exercise quantity over today's exercise quota, cannot approve", "Exercise quantity over today's exercise quota, cannot approve", "该条OPTION行权数量已超过今日可用行权数量，无法通过审核", "該條OPTION行權數量已超過今日可用行權數量，無法通過審核");
        public static final Coder NO_QUOTA_REMAIN = new Coder("EX031", "No exercise quota remain today", "No exercise quota remain today", "今日行权额度已用完", "今日行權額度已用完");
        public static final Coder OVER_COMPANY_UPPER_LIMIT_BATCH = new Coder("EX032", "Exercise quantity over today's exercise quota, cannot approve", "Exercise quantity over today's exercise quota, cannot approve", "选中OPTION行权数量之和已超过今日可用行权数量，无法通过审核", "選中OPTION行權數量之和已超過今日可用行權數量，無法通過審核");
    }

    public static class CompanyRate {
        public static final Coder RATE_MONTH_ERROR = new Coder("RT001", "Please set the exchange rate for the month of the vesting date first, and then conduct vesting review", "Please set the exchange rate for the month of the vesting date first, and then conduct vesting review", "请先设置归属日期所在月份的汇率，再进行归属审核", "請先設置歸屬日期所在月份的匯率，再進行歸屬審核");
        public static final Coder RATE_ADD_ERROR = new Coder("RT002", "The exchange rate data for this month has been added, please refresh the page and try again", "The exchange rate data for this month has been added, please refresh the page and try again", "该月汇率数据已添加，请刷新页面重试", "該月匯率數據已添加，請刷新頁面重試");
        public static final Coder RATE_UPDATE_ERROR = new Coder("RT003", "The exchange rate data of the month has been changed, please refresh the page and try again", "The exchange rate data of the month has been changed, please refresh the page and try again", "该月汇率数据已更改，请刷新页面重试", "該月匯率數據已更改，請刷新頁面重試");
        public static final Coder SAVE_RATE_ERROR = new Coder("RT004", "Failed to save company exchange rate information", "Failed to save company exchange rate information", "保存公司汇率信息失败", "保存公司匯率信息失敗");
        public static final Coder SAVE_ACTIVE_ERROR = new Coder("RT005", "The exchange rate information failed to take effect", "The exchange rate information failed to take effect", "该条汇率信息生效失败", "該條匯率信息生效失敗");
    }

    public static class System {
        public static final Coder REPORT_CANCEL_NAME = new Coder("report.canceled.name", "Cancel Report", "Cancel Report", "取消报表", "取消報表");
        public static final Coder REPORT_HOLDING_NAME = new Coder("report.holding.name", "Holding Report", "Holding Report", "持有报表", "持有報表");
        public static final Coder REPORT_VESTED_NAME = new Coder("report.vested.name", "Vested Report", "Vested Report", "已归属报表", "已歸屬報表");
        public static final Coder REPORT_VESTING_NOTIFY_NAME = new Coder("report.vesting_notify.name", "Vesting Notification Report", "Vesting Notification Report", "归属通知报表", "歸屬通知報表");
        public static final Coder REPORT_TAX_NAME = new Coder("report.tax.name", "Tax Report", "Tax Report", "计税报表", "計稅報表");
        public static final Coder REPORT_EXERCISE_NAME = new Coder("report.exercise.name", "Exercise Report", "Exercise Report", "行权报表", "行權報表");
    }

    public static class Report {
        public static class Canceled {
            public static final Coder COLUMN_NAME1 = new Coder("report.canceled.column1", "Grant number", "Grant number", "授予编号", "授予編號");
            public static final Coder COLUMN_NAME2 = new Coder("report.canceled.column2", "Name", "Name", "姓名", "姓名");
            public static final Coder COLUMN_NAME3 = new Coder("report.canceled.column3", "Work number", "Work number", "工号", "工號");
            public static final Coder COLUMN_NAME4 = new Coder("report.canceled.column4", "Grant date", "Grant date", "授予日期", "授予日期");
            public static final Coder COLUMN_NAME5 = new Coder("report.canceled.column5", "Grant number", "Grant number", "授予数量", "授予數量");
            public static final Coder COLUMN_NAME6 = new Coder("report.canceled.column6", "Grant price", "Grant price", "授予价（港币）", "授予價（港幣）");
            public static final Coder COLUMN_NAME7 = new Coder("report.canceled.column7", "Determine the date", "Determine the date", "判断日期", "判斷日期");
            public static final Coder COLUMN_NAME8 = new Coder("report.canceled.column8", "Pending cancellation", "Pending cancellation", "待归属取消", "待歸屬取消");
        }

        public static class Hold {
            public static final Coder COLUMN_NAME1 = new Coder("report.hold.column1", "Grant number", "Grant number", "授予编号", "授予編號");
            public static final Coder COLUMN_NAME2 = new Coder("report.hold.column2", "Name", "Name", "姓名", "姓名");
            public static final Coder COLUMN_NAME3 = new Coder("report.hold.column3", "Work number", "Work number", "工号", "工號");
            public static final Coder COLUMN_NAME4 = new Coder("report.hold.column4", "Grant date", "Grant date", "授予日期", "授予日期");
            public static final Coder COLUMN_NAME5 = new Coder("report.hold.column5", "Grant number", "Grant number", "授予数量", "授予數量");
            public static final Coder COLUMN_NAME6 = new Coder("report.hold.column6", "Grant price", "Grant price", "授予价（港币）", "授予價（港幣）");
            public static final Coder COLUMN_NAME7 = new Coder("report.hold.column7", "Has belongs to", "Has belongs to", "已归属", "已歸屬");
            public static final Coder COLUMN_NAME8 = new Coder("report.hold.column8", "To belong to", "To belong to", "待归属", "待歸屬");
            public static final Coder COLUMN_NAME9 = new Coder("report.hold.column9", "Pending cancellation", "Pending cancellation", "待归属取消", "待歸屬取消");
        }

        public static class Vest {
            public static final Coder COLUMN_NAME1 = new Coder("report.vested.column1", "Grant number", "Grant number", "授予编号", "授予編號");
            public static final Coder COLUMN_NAME2 = new Coder("report.vested.column2", "Name", "Name", "姓名", "姓名");
            public static final Coder COLUMN_NAME3 = new Coder("report.vested.column3", "Work number", "Work number", "工号", "工號");
            public static final Coder COLUMN_NAME4 = new Coder("report.vested.column4", "Grant date", "Grant date", "授予日期", "授予日期");
            public static final Coder COLUMN_NAME5 = new Coder("report.vested.column5", "Grant number", "Grant number", "授予数量", "授予數量");
            public static final Coder COLUMN_NAME6 = new Coder("report.vested.column6", "Grant price", "Grant price", "授予价（港币）", "授予價（港幣）");
            public static final Coder COLUMN_NAME7 = new Coder("report.vested.column7", "Vesting date", "Vesting date", "归属日期", "歸屬日期");
            public static final Coder COLUMN_NAME8 = new Coder("report.vested.column8", "Number of belonging", "Number of belonging", "归属数量", "歸屬數量");

        }
    }

    public static class Taxation {
        public static final Coder SELECTED_DATA_IS_ORIGINAL = new Coder("TA001", "The selected data is already the original value", "The selected data is already the original value", "选中数据已经是原始值", "選中數據已經是原始值");
    }
}
