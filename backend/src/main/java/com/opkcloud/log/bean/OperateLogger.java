package com.opkcloud.log.bean;

public class OperateLogger {
    private String numId;

    private String regId;

    private String userId;

    private String organization;

    private String organizationId;

    private String userName;

    private String operateTime;

    private String terminalId;

    private Integer operateType;

    private String operateResult;

    private String errorCode;

    private String operateName;

    private String operateCondition;

    private Integer operateNumber;

    private String operateTable;

    private String operateKey;

    private String msg;

    private String operatenape;

    private String path;

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId == null ? null : numId.trim();
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId == null ? null : regId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime == null ? null : operateTime.trim();
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId == null ? null : terminalId.trim();
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult == null ? null : operateResult.trim();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName == null ? null : operateName.trim();
    }

    public String getOperateCondition() {
        return operateCondition;
    }

    public void setOperateCondition(String operateCondition) {
        this.operateCondition = operateCondition == null ? null : operateCondition.trim();
    }

    public Integer getOperateNumber() {
        return operateNumber;
    }

    public void setOperateNumber(Integer operateNumber) {
        this.operateNumber = operateNumber;
    }

    public String getOperateTable() {
        return operateTable;
    }

    public void setOperateTable(String operateTable) {
        this.operateTable = operateTable == null ? null : operateTable.trim();
    }

    public String getOperateKey() {
        return operateKey;
    }

    public void setOperateKey(String operateKey) {
        this.operateKey = operateKey == null ? null : operateKey.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getOperatenape() {
        return operatenape;
    }

    public void setOperatenape(String operatenape) {
        this.operatenape = operatenape == null ? null : operatenape.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}