//package com.opkcloud.util.logs;
//
//import com.alibaba.druid.sql.visitor.functions.Char;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonSyntaxException;
//import com.google.gson.reflect.TypeToken;
//import com.opkcloud.pojo.OperateLogger;
//import com.opkcloud.user.bean.User;
//import com.opkcloud.service.OperateLoggerService;
//import net.sf.json.JSONObject;
//import org.apache.commons.collections.CollectionUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.thymeleaf.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.*;
//
//@Aspect
//@Component
//public class SystemLogAspect {
//
//    @Autowired
//    private OperateLoggerService operateLoggerService;
//
//    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
//
//    /**
//     * 操作日志
//     */
//    @Pointcut("@annotation(com.opkcloud.util.logs.SystemControllerLog)")
//    public void controllerAspect() {}
//
//    @Before("controllerAspect()")
//    public void doBeforeController(JoinPoint point) {}
//
//    /**
//     * 控制器异常通知
//     * @param point
//     * @param ex
//     */
//    @AfterThrowing(pointcut = "controllerAspect()", throwing = "ex")
//    public void deAfterThrowingInController(JoinPoint point, Exception ex) {
//        ex.printStackTrace();
//        logger.error("异常日志" + point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "()");
//        logger.error(ex.getLocalizedMessage());
//        logger.error(ex.getMessage());
//    }
//
//    /**
//     * 控制器环绕通知
//     * @param joinPoint
//     * @return
//     */
//    @Around("controllerAspect()")
//    public Object doAroundController(ProceedingJoinPoint joinPoint) {
//        Object obj = null;
//        Object[] arguments = joinPoint.getArgs();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        OperateLogger entity = new OperateLogger();
////        User user = (User) request.getSession().getAttribute(UserEmun.USER_KEY.getCode());
//
//        User user = new User();
//        user.setId(111);
//        user.setAge(123);
//        user.setName("opk");
//
//        entity.setPath(request.getRequestURI());
//        for (int i = 0; i< arguments.length; i++) {
//            try {
//                entity.setOperateCondition(JSONObject.fromObject(arguments[i]).toString());
//            } catch (Exception e) {
//                continue;
//            }
//        }
//
//        MethodLogAnnon auth = null;
//        Map<String, Object> params = argsContent(arguments, auth, entity);
//        GsonBuilder builder = new GsonBuilder();
//        String json = builder.create().toJson(params);
//
//        String oper[] = getControllerMethodDescription(joinPoint, request.getMethod());
//        entity.setOperateType(Integer.valueOf(oper[0]));
//        entity.setOperateName(oper[1]);
//        entity.setOperatenape(oper[2]);
//        Map<String, String[]> map = request.getParameterMap();
//        Set<String> keys = map.keySet();
//        Iterator<String> iterator = keys.iterator();
//        List<String> list = new ArrayList<String>();
//        StringBuilder str = new StringBuilder();
//        str.append("{");
//        while (iterator.hasNext()) {
//            String key = iterator.next().toString();
//            if (request.getParameter(key) != null && !"".equals(request.getParameter(key).trim())) {
//                String value = request.getParameter(key).replaceAll("", "\"").trim();
//                value = new String(AsynClient.getURLEncoder(value));
//                String Decoder = AsynClient.getURLDecoder(value);
////                list.add(Decoder);
//                if (request.getMethod().equals("GET")) {
//                    str.append("\"" + key + "\"" + ":" + "\"" + value + "\"" + ";");
//                }
//            }
//        }
//        str.append("}");
//        if (request.getMethod().equals("GET")) {
//            entity.setOperateCondition(json);
//        }
//        entity.setOperateCondition(json);
//
//        // 请求的IP
//        String ip = getIPAddr(request);
//        entity.setRegId("");
//        entity.setTerminalId(ip);
//
//        if (logger.isInfoEnabled()) {
//            logger.info("===== 日志开始 =====");
//            logger.info("请求方法：" + (joinPoint.getTarget().getClass().getName()) + "." + joinPoint.getSignature().getName() + "()");
//            logger.info("方法描述：" + oper[1]);
//            logger.info("请求IP：" + ip);
//        }
//
////        entity.setOperateCondition(list.toString() + "");
//
//        try {
//            obj = joinPoint.proceed();
//            entity.setOperateResult("1");
//            entity.setMsg("success");
//        } catch (Throwable e) {
//            entity.setOperateResult("0");
//            entity.setErrorCode("2000");
//            entity.setMsg(StringUtils.isEmpty(e.getMessage()) ? "failed"
//                    : e.getMessage().substring(0, e.getMessage().length() > 300 ? 300 : e.getMessage().length()));
//            logger.error("controllerAspect", e);
//            throw new RuntimeException(e.getMessage());
//        } finally {
//            try {
//                user.setId(111);
////                LoggerThread thread = new LoggerThread(entity, user);
////                new Thread(thread).start();
//                operateLoggerService.addOperateLogger(entity);
//            } catch (Exception e) {
//                logger.error("插入日志异常：" + e.getLocalizedMessage());
//            }
//        }
//        return obj;
//    }
//
//    /**
//     * 获取注解中对方法的描述信息 用于Controller层注解
//     * @param joinPoint 切点
//     * @param reMethod 方法描述
//     * @return
//     */
//    public static String[] getControllerMethodDescription(JoinPoint joinPoint, String reMethod) {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        StringBuilder str = new StringBuilder();
//        String description = "";
//        String operateName = "";
//        String operateNape = "";
//        Object[] arguments = joinPoint.getArgs();
//        Class<?> targetClass;
//        try {
//            targetClass = Class.forName(targetName);
//            Method[] methods = targetClass.getMethods();
//            for (Method method : methods) {
//                if (method.getName().equals(methodName)) {
//                    Class<?>[] clazzs = method.getParameterTypes();
//                    if (clazzs.length == arguments.length) {
//                        description = method.getAnnotation(SystemControllerLog.class).description();
//                        operateName = method.getAnnotation(SystemControllerLog.class).operateName();
//                        operateNape = method.getAnnotation(SystemControllerLog.class).operateNape();
//                        break;
//                    }
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return new String[] {description, operateName, operateNape, str.toString()};
//    }
//
//    public static String getIPAddr(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//
//    @SuppressWarnings("serial")
//    public Map<String, Object> argsContent(Object[] args, MethodLogAnnon annon, OperateLogger entity) {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        Map<String, Object> chineseParams = new HashMap<String, Object>();
//
//        if (args == null || args.length == 0) {
//            return null;
//        }
//
//        for (Object obj:args) {
//            if (obj == null) {
//                continue;
//            }
//            GsonBuilder builder = new GsonBuilder();
//            resultMap.put("参数", buildParams(chineseParams, builder, obj));
//        }
//        return chineseParams;
//    }
//
//    @SuppressWarnings("serial")
//    private Map<String, Object> buildParams(Map<String, Object> chineseParams, GsonBuilder builder, Object dataObj) {
//        if (dataObj == null) return null;
//        try {
//            Field fields[] = dataObj.getClass().getDeclaredFields();
//            Method[] methods = dataObj.getClass().getDeclaredMethods();
//            for (Method method:methods) {
//                if (method.getName().startsWith("get")) {
//                    Object resultObj = method.invoke(dataObj, new Object[]{});
//                    if (resultObj != null) {
//                        boolean isPut = false;
//                        String name = null;
//                        for (Field field:fields) {
//                            name = null;
//                            boolean isAnnon = field.isAnnotationPresent(RecordAnnon.class);
//                            if (isAnnon) {
//                                if (field.getName().equals(method.getName().substring(3, 4).toLowerCase()
//                                        + method.getName().substring(4, method.getName().length()))) {
//                                    RecordAnnon apiModelProperty = field.getAnnotation(RecordAnnon.class);
//                                    if (!(resultObj instanceof String || resultObj instanceof Integer
//                                        || resultObj instanceof Long || resultObj instanceof Char
//                                        || resultObj instanceof Float || resultObj instanceof Double
//                                        || resultObj instanceof Boolean || resultObj instanceof Byte
//                                        || resultObj instanceof Short || resultObj instanceof Map
//                                        || resultObj instanceof List<?>)) {
//                                        Object newObject = builder.create().fromJson(builder.create().toJson(resultObj), apiModelProperty.clazz());
//                                        chineseParams.put(apiModelProperty.value(), buildParams(newObject, builder, chineseParams));
//                                        isPut = true;
//                                    } else if (resultObj instanceof List<?>) {
//                                        List<Object> resultObjectList = builder.create().fromJson(builder.create().toJson(resultObj), new TypeToken<List<?>>() {}.getType());
//                                        if (CollectionUtils.isNotEmpty(resultObjectList)) {
//                                            List<Object> listObj = new ArrayList<Object>();
//                                            for (Object object :resultObjectList) {
//                                                object = builder.create().fromJson(builder.create().toJson(object), apiModelProperty.clazz());
//                                                if (!(object instanceof String || object instanceof Integer
//                                                    || object instanceof Long || object instanceof Char
//                                                    || object instanceof Float || object instanceof Double
//                                                    || object instanceof Boolean || object instanceof Byte
//                                                    || object instanceof Short || object instanceof Map
//                                                    || object instanceof List<?>)) {
//                                                    listObj.add(buildParams(object, builder, chineseParams));
//                                                } else {
//                                                    listObj.add(object);
//                                                }
//                                            }
//                                            chineseParams.put(apiModelProperty.value(), listObj);
//                                        }
//                                        isPut = true;
//                                    } else {
//                                        name = apiModelProperty.value();
//                                    }
//                                    break;
//                                }
//                            }
//                        }
//                        if (!isPut) {
//                            chineseParams.put(name, resultObj);
//                        }
//                    }
//                }
//            }
//        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//                | JsonSyntaxException e) {
//            // | InstantiationException
//            logger.info("参数解析错误{}", e.getMessage());
//        }
//        return chineseParams;
//    }
//
//    @SuppressWarnings("serial")
//    private Object buildParams(Object dataObj, GsonBuilder builder, Map<String, Object> result)
//            throws InvocationTargetException, IllegalAccessException {
//        if (dataObj == null) return null;
//        Field fields[] = dataObj.getClass().getDeclaredFields();
//        Method[] methods = dataObj.getClass().getDeclaredMethods();
//        Map<String, Object> jsonObj = new HashMap<String, Object>();
//        for (Method method : methods) {
//            if (method.getName().startsWith("get")) {
//                Object resultObj = method.invoke(dataObj, new Object[]{});
//                if (resultObj != null) {
//                    for (Field field : fields) {
//                        boolean isAnnon = field.isAnnotationPresent(RecordAnnon.class);
//                        if (isAnnon) {
//                            if (field.getName().equals(method.getName().substring(3, 4).toLowerCase()
//                                    + method.getName().substring(4, method.getName().length()))) {
//                                RecordAnnon apiModelProperty = field.getAnnotation(RecordAnnon.class);
//                                if (!(resultObj instanceof String || resultObj instanceof  Integer
//                                    || resultObj instanceof Long || resultObj instanceof Char
//                                    || resultObj instanceof Float || resultObj instanceof Double
//                                    || resultObj instanceof Boolean || resultObj instanceof Byte
//                                    || resultObj instanceof Short || resultObj instanceof Map
//                                    || resultObj instanceof List<?>)) {
//                                    Object newObject = builder.create().fromJson(builder.create().toJson(resultObj), apiModelProperty.clazz());
//                                    jsonObj.put(apiModelProperty.value(), buildParams(newObject, builder, jsonObj));
//                                } else if (resultObj instanceof List<?>) {
//                                    List<?> resultObjectList = builder.create().fromJson(builder.create().toJson(resultObj), new TypeToken<List<?>>() {
//                                    }.getType());
//                                    if (CollectionUtils.isNotEmpty(resultObjectList)) {
//                                        List<Object> listObj = new ArrayList<Object>();
//                                        for (Object object : resultObjectList) {
//                                            object = builder.create().fromJson(builder.create().toJson(object), apiModelProperty.clazz());
//                                            if (!(object instanceof  String || object instanceof Integer
//                                                || object instanceof Long || object instanceof Char
//                                                || object instanceof Float || object instanceof Double
//                                                || object instanceof Boolean || object instanceof Byte
//                                                || object instanceof Short || object instanceof Map
//                                                || object instanceof List<?>)) {
//                                                listObj.add(buildParams(object, builder, jsonObj));
//                                            } else {
//                                                listObj.add(object);
//                                            }
//                                        }
//                                        jsonObj.put(apiModelProperty.value(), listObj);
//                                    }
//                                } else {
//                                    jsonObj.put(apiModelProperty.value(), resultObj);
//                                }
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return jsonObj;
//    }
//
//}
