
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BCZ {


    public static void main(String[] args) {

        List<String> topicList = new ArrayList<>();
        List<String> topicList1 = new ArrayList<>();
        List<String> topicList4 = new ArrayList<>();
        List<String> topicList5 = new ArrayList<>();
        Connection conn = connect();
        String sql = "select * from topic_book_map where book_ids like \"%13%\"";//将从表中查询到的的所有信息存入sql
        try {
            Statement stmt = conn.createStatement();//得到Statement实例
            ResultSet rs = stmt.executeQuery(sql);//执行SQL语句返回结果集
            while (rs.next()) {
                //输出获得记录中的"name","sex","age"字段的值
                String topicId = rs.getString("topic_id");
                String bookIds = rs.getString("book_ids");
                String[] dd = bookIds.split(",");
                int flag = 0;
                for (String item : dd) {
                    if (item.equals("21") && flag == 0) {
                        topicList.add(topicId);
                        flag = 1;
                    }
                }
            }
            System.out.println("topic数量" + topicList.size());

            for (String item : topicList) {
//                String sqlll="select * from dict_a_b where topic_id=\""+item+"\";";
//                ResultSet rs1 = stmt.executeQuery(sqlll);
//                if(rs1.next()){
//                    topicList1.add(rs1.getString("word"));
//                }


                String sqll3 = "select * from dict_bcz where topic_id=\"" + item + "\";";
                ResultSet rs3 = stmt.executeQuery(sqll3);
                if (rs3.next()) {
                    topicList1.add(rs3.getString("word"));
                }

//                String sqll4="select * from dict_c where topic_id=\""+item+"\";";
//                ResultSet rs4 = stmt.executeQuery(sqll4);
//                if(rs4.next()){
//                    topicList1.add(rs4.getString("word"));
//                }

//                String sqll5="select * from dict_d_f where topic_id=\""+item+"\";";
//                ResultSet rs5 = stmt.executeQuery(sqll5);
//                if(rs5.next()){
//                    topicList1.add(rs5.getString("word"));
//                }
//                String sqll6="select * from dict_g_k where topic_id=\""+item+"\";";
//                ResultSet rs6 = stmt.executeQuery(sqll6);
//                if(rs6.next()){
//                    topicList1.add(rs6.getString("word"));
//                }

//                String sqll7="select * from dict_l_o where topic_id=\""+item+"\";";
//                ResultSet rs7 = stmt.executeQuery(sqll7);
//                if(rs7.next()){
//                    topicList1.add(rs7.getString("word"));
//                }
//                String sqll8="select * from dict_p_r where topic_id=\""+item+"\";";
//                ResultSet rs8 = stmt.executeQuery(sqll8);
//                if(rs8.next()){
//                    topicList1.add(rs8.getString("word"));
//                }
//                String sqll9="select * from dict_s where topic_id=\""+item+"\";";
//                ResultSet rs9 = stmt.executeQuery(sqll9);
//                if(rs9.next()){
//                    topicList1.add(rs9.getString("word"));
//                }
//                String sqll2="select * from dict_t_z where topic_id=\""+item+"\";";
//                ResultSet rs2 = stmt.executeQuery(sqll2);
//                if(rs2.next()){
//                    topicList1.add(rs2.getString("word"));
//                }
            }

            System.out.println("单词数量" + topicList1.size());

        } catch (SQLException e) {

            System.out.println("查询数据时出错！" + e.getMessage());

        }


        try {
            Connection conn1 = connect1();
            Statement stmtww = conn1.createStatement();//得到Statement实例
            for (String item : topicList1) {
                String sqlwww1 = "select * from tb_collect_words where word=\"" + item + "\";";
                ResultSet rsff = stmtww.executeQuery(sqlwww1);

                int flag = 0;

                while (rsff.next()) {
                    flag = 1;
                }
                if (flag == 0) {
                    topicList4.add(item);
                }
            }
            System.out.println("未学习单词数量" + topicList4.size());
        } catch (SQLException e) {
            System.out.println("查询数据时出错！" + e.getMessage());
        }
        String json = "";
        String json1 = "";
        File[] files = null;
        try {
            File file = new File("C:\\Users\\dingyingsi\\Desktop\\baicizhan\\zpack\\19\\0");
            files = file.listFiles();
        } catch (Exception e) {
        }

        for (File item : files) {
            try {
                String ffff = null;
                ffff = FileUtils.readFileToString(item, "UTF-8");
                json = StringUtils.substringBefore(StringUtils.substringAfter(ffff, "{\"topic_id\""),"\"}");

                json1 = "{\"topic_id\"" + json + "\"}";

                JSONObject outJson = JSONObject.parseObject(json1);
                String wordfff = outJson.getString("word");
                String accent = outJson.getString("accent");
                String mean_cn = outJson.getString("mean_cn");
                String sentence = outJson.getString("sentence");
                String sentence_trans = outJson.getString("sentence_trans");
                String res = sentence + "#" + sentence_trans + "#" + wordfff + "," + accent + "#" + mean_cn;
                System.out.println(res);

                for (String item1 : topicList4) {
                    if (wordfff.equals(item1)) {
                        topicList5.add(res);
                    }
                }

//                    System.out.println(wordfff);

            } catch (Exception e) {

                json = StringUtils.substringBefore(StringUtils.substringAfter("{\"topic_id\"" + json, "{\"topic_id\""), "}}");

                json1 = "{\"topic_id\"" + json + "}}";

                JSONObject outJson = JSONObject.parseObject(json1);
                String wordfff = outJson.getString("word");
                String accent = outJson.getString("accent");
                String mean_cn = outJson.getString("mean_cn");
                mean_cn = mean_cn.replace(",", "，");
                String sentence = outJson.getString("sentence");
                sentence = sentence.replace(",", "，");
                String sentence_trans = outJson.getString("sentence_trans");
                sentence_trans = sentence_trans.replace(",", "，");

                String res = sentence + "#" + sentence_trans + "#" + wordfff + "," + accent + "#" + mean_cn;

                System.out.println(res);

                for (String item1 : topicList4 ) {
                    if (wordfff.equals(item1)) {
                        topicList5.add(res);
                    }
                }
                // e.printStackTrace();
            }
        }
        System.out.println(topicList5.size());
        try {
            FileUtils.writeLines(new File("C:\\Users\\dingyingsi\\Desktop\\weixuexi1.csv"), topicList5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(encryption("123456", "12345688"));

    }

    private static Connection connect1() {
        Connection conn = null;//定义数据库连接对象
        try {
            String url = "jdbc:sqlite:C:\\Users\\dingyingsi\\Desktop\\baicizhan\\baicizhandoexampleinfo.db";   //定义连接数据库的url(url:访问数据库的URL路径),test为数据库名称
            Class.forName("org.sqlite.JDBC");//加载数据库驱动
            conn = DriverManager.getConnection(url);    //获取数据库连接
            System.out.println("数据库连接成功！\n");//数据库连接成功输出提示
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("数据库连接失败！" + e.getMessage());
        }
        return conn;//返回一个连接
    }


    private static Connection connect() {
        Connection conn = null;//定义数据库连接对象
        try {
            String url = "jdbc:sqlite:C:\\Users\\dingyingsi\\Desktop\\baicizhan\\lookup.db";   //定义连接数据库的url(url:访问数据库的URL路径),test为数据库名称
            Class.forName("org.sqlite.JDBC");//加载数据库驱动
            conn = DriverManager.getConnection(url);    //获取数据库连接
            System.out.println("数据库连接成功！\n");//数据库连接成功输出提示
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("数据库连接失败！" + e.getMessage());
        }
        return conn;//返回一个连接
    }
    public static String encryption(String plainData, String secretKey) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(secretKey.getBytes("UTF-8"));
            cipher.init(1, generateKey(secretKey), ivParameterSpec);
            byte[] buf = cipher.doFinal(plainData.getBytes());
            result = Base64.encodeBase64String(buf);
        } catch (Exception var6) {
//            logger.error(var6.getMessage(), var6);
        }
        return result;
    }
    public static String decryption(String secretData, String secretKey) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(secretKey.getBytes("UTF-8"));
            cipher.init(2, generateKey(secretKey), ivParameterSpec);
            byte[] buf = cipher.doFinal(Base64.decodeBase64(secretData));
            result = new String(buf, "utf-8");
        } catch (Exception var6) {
            // logger.error(var6.getMessage(), var6);
        }
        return result;
    }

    private static SecretKey generateKey(String secretKey) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes());
            return keyFactory.generateSecret(keySpec);
        } catch (Exception var3) {
//            logger.error(var3.getMessage(), var3);
            throw new RuntimeException("generate secretKey fail !!");
        }
    }

    private static String byteArray2HexStingr(byte[] bs) {
        StringBuffer sb = new StringBuffer();
        byte[] arrayOfByte = bs;
        int j = bs.length;
        for (int i = 0; i < j; ++i) {
            byte b = arrayOfByte[i];
            sb.append(byte2HexString(b));
        }
        return sb.toString();
    }


    private static String byte2HexString(byte b) {
        String hexStr = null;
        int n = b;
        if (b < 0) {
            n = b & 255;
        }
        hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
        return hexStr.toUpperCase();
    }


    public static String md5Hex(String origString) {
        String origMD5 = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] result = md5.digest(origString.getBytes("UTF-8"));
            origMD5 = byteArray2HexStingr(result);
        } catch (Exception var4) {
//            logger.error(var4.getMessage(), var4);
        }
        return origMD5;
    }

    public static String encodePassword(String rawPass, Object salt) {
        String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
        MessageDigest messageDigest = getMessageDigest();
        byte[] digest = messageDigest.digest(encode(saltedPass));
        return new String(encode(digest));
    }


    protected static final MessageDigest getMessageDigest() throws IllegalArgumentException {
        try {
            return MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException var1) {
            throw new IllegalArgumentException("No such algorithm [1]");
        }
    }

    protected static String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }

        if (strict && salt != null && (salt.toString().lastIndexOf("{") != -1 || salt.toString().lastIndexOf("}") != -1)) {
            throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
        } else {
            return salt != null && !"".equals(salt) ? password + "{" + salt.toString() + "}" : password;
        }
    }

    private static final Charset CHARSET = Charset.forName("UTF-8");

    public static byte[] encode(CharSequence string) {

        try {
            ByteBuffer bytes = CHARSET.newEncoder().encode(CharBuffer.wrap(string));
            byte[] bytesCopy = new byte[bytes.limit()];
            System.arraycopy(bytes.array(), 0, bytesCopy, 0, bytes.limit());
            return bytesCopy;
        } catch (CharacterCodingException var3) {
            throw new IllegalArgumentException("Encoding failed", var3);
        }
    }


    public static String decode(byte[] bytes) {
        try {
            return CHARSET.newDecoder().decode(ByteBuffer.wrap(bytes)).toString();
        } catch (CharacterCodingException var2) {
            throw new IllegalArgumentException("Decoding failed", var2);
        }
    }


    private static final char[] HEX = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static char[] encode(byte[] bytes) {
        int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];
        int j = 0;

        for (int i = 0; i < nBytes; ++i) {
            result[j++] = HEX[(240 & bytes[i]) >>> 4];
            result[j++] = HEX[15 & bytes[i]];
        }
        return result;
    }

    public static byte[] decode(CharSequence s) {
        int nChars = s.length();
        if (nChars % 2 != 0) {
            throw new IllegalArgumentException("Hex-encoded string must have an even number of characters");
        } else {
            byte[] result = new byte[nChars / 2];
            for (int i = 0; i < nChars; i += 2) {
                int msb = Character.digit(s.charAt(i), 16);
                int lsb = Character.digit(s.charAt(i + 1), 16);
                if (msb < 0 || lsb < 0) {
                    throw new IllegalArgumentException("Non-hex character in input: " + s);
                }
                result[i / 2] = (byte) (msb << 4 | lsb);
            }
            return result;
        }
    }
}
