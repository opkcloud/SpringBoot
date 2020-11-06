//package opkcloud.util;
//
//import ObjectsTranscoder;
//import opkcloud.util.redis.RedisManager;
//import redis.clients.jedis.Jedis;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedOutputStream;
//import java.io.UnsupportedEncodingException;
//
//public class FileOperateUtil {
//
//    /**
//     * 存入缓存
//     * @param key
//     * @param obj
//     */
//    public static void setCacheCommon(String key, Object obj) {
//        if (obj != null) {
//            Jedis jedis = RedisManager.getJedisPool().getResource();
//            jedis.set(key.getBytes(), ObjectsTranscoder.serialize(obj));
//            RedisManager.getJedisPool().returnResource(jedis);
//        }
//    }
//
//    /**
//     * 得到缓存
//     * @param key
//     * @return
//     */
//    public static Object getCacheCommon(String key) {
//        Object obj = null;
//        Jedis jedis = RedisManager.getJedisPool().getResource();
//        byte[] in = jedis.get(key.getBytes());
//        if (null != in) {
//            obj = ObjectsTranscoder.deSerialize(in);
//        }
//        RedisManager.getJedisPool().returnResource(jedis);
//        return obj;
//    }
//
//    // 表单提交
//    public static void downLoad(HttpServletRequest request, HttpServletResponse response, String fileName, byte[] bytes) throws Exception {
//        try {
//            request.setCharacterEncoding("UTF-8");
//            BufferedOutputStream bos = null;
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-disposition", "attachement;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
//            bos.write(bytes);
//            bos = new BufferedOutputStream(response.getOutputStream());
//            bos.close();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            throw new Exception();
//        }
//    }
//
//}
