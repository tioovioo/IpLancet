package com.lancet.iplancet.util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author yongjia.guo
 * @create 2017-03-27 上午10:53
 **/
@SuppressWarnings("all")
public class StringUtil {
   public static Pattern pattern = Pattern.compile("[\u4e00-\u9fa5A-Za-z0-9]");
    /**
     * 判断字符串为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static String emptyToSpace(String s) {
        return isEmpty(s) ? " " : s;
    }

    /**
     * 先去空格再判断非空
     *
     * @param str
     * @return
     */
    public static boolean isClearEmpty(String str) {
        str = StringUtil.toClearStr(str);
        return StringUtil.isEmpty(str);
    }

    /**
     * 判断字符串中的有效字符长度
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isTooLong(String str, Integer length) {
        int count = 0;
        char c[] = str.toCharArray();
        for (char aC : c) {
            Matcher matcher = pattern.matcher(String.valueOf(aC));
            if (matcher.matches()) {
                System.out.println(aC);
                count++;
            }
        }
        return count > length;
    }

    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str.trim();
    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 删除字符串前后多余双引号
     *
     * @param string
     * @return
     */
    public static String toClearStr(String string) {
        if (isEmpty(string)) {
            return string;
        }
        string = string.replaceAll("　", "");
        Pattern p = compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(string);
        string = m.replaceAll("");
        return string.trim();
    }

    /**
     * 替换json中的字段
     *
     * @param str
     * @return
     */
    public static String replaceMedicalRecordField(String str) {

        return str
                .replaceFirst("emrId", "emr_id")
//                .replaceFirst("maritalStatus", "marital_status")
                .replaceFirst("admissionTime", "admission_time")
                .replaceFirst("dischargeTime", "discharge_time")
                .replaceFirst("mainDiagnosis", "main_diagnosis")
//                .replaceFirst("nativePlace", "native_place")
//                .replaceFirst("admissionDiagnosis", "admission_diagnosis")
//                .replaceFirst("dischargeDiagnosis", "discharge_diagnosis")
//                .replaceFirst("personalHistory", "personal_history")
//                .replaceFirst("familyHistory", "family_history")
//                .replaceFirst("presentHistory", "present_history")
//                .replaceFirst("pastHistory", "past_history")
//                .replaceFirst("physicalExamination", "physical_examination")
//                .replaceFirst("accessoryExamination", "accessory_examination")
//                .replaceFirst("obstericalHistory", "obsterical_history")
//                .replaceFirst("menstruationHistory", "menstruation_history")
//                .replaceFirst("createUser", "create_user")
                .replaceFirst("createTime", "create_time")
                .replaceFirst("responsibleDoctor", "responsible_doctor")
                .replaceFirst("personName", "person_name")
                .replaceFirst("hasBug", "has_bug")
                .replaceFirst("admissionDepartment", "admission_department")
                .replaceFirst("dischargeDepartment", "discharge_department");
    }

    private static final HashMap<Integer, String> CONTENTID_TYPE = new HashMap<>();

    static {
        CONTENTID_TYPE.put(1, "内涵质控");
        CONTENTID_TYPE.put(2, "内涵质控");
        CONTENTID_TYPE.put(3, "形式质控");
        CONTENTID_TYPE.put(4, "形式质控");
        CONTENTID_TYPE.put(5, "形式质控");
        CONTENTID_TYPE.put(6, "形式质控");
        CONTENTID_TYPE.put(7, "内涵质控");
        CONTENTID_TYPE.put(8, "内涵质控");
        CONTENTID_TYPE.put(9, "内涵质控");
        CONTENTID_TYPE.put(10, "形式质控");
        CONTENTID_TYPE.put(11, "内涵质控");
        CONTENTID_TYPE.put(12, "内涵质控");
        CONTENTID_TYPE.put(13, "内涵质控");
        CONTENTID_TYPE.put(14, "形式质控");
        CONTENTID_TYPE.put(15, "内涵质控");
        CONTENTID_TYPE.put(16, "内涵质控");
        CONTENTID_TYPE.put(17, "内涵质控");
        CONTENTID_TYPE.put(18, "形式质控");
        CONTENTID_TYPE.put(19, "形式质控");
        CONTENTID_TYPE.put(20, "内涵质控");
        CONTENTID_TYPE.put(21, "形式质控");
        CONTENTID_TYPE.put(22, "内涵质控");
        CONTENTID_TYPE.put(23, "形式质控");
        CONTENTID_TYPE.put(24, "内涵质控");
        CONTENTID_TYPE.put(25, "形式质控");
        CONTENTID_TYPE.put(26, "内涵质控");
        CONTENTID_TYPE.put(27, "内涵质控");
        CONTENTID_TYPE.put(28, "内涵质控");
        CONTENTID_TYPE.put(29, "形式质控");
        CONTENTID_TYPE.put(30, "形式质控");
        CONTENTID_TYPE.put(31, "形式质控");
        CONTENTID_TYPE.put(32, "内涵质控");
        CONTENTID_TYPE.put(33, "内涵质控");
        CONTENTID_TYPE.put(34, "形式质控");
        CONTENTID_TYPE.put(35, "形式质控");
        CONTENTID_TYPE.put(36, "内涵质控");
        CONTENTID_TYPE.put(37, "内涵质控");
        CONTENTID_TYPE.put(38, "形式质控");
        CONTENTID_TYPE.put(39, "形式质控");
        CONTENTID_TYPE.put(40, "内涵质控");
        CONTENTID_TYPE.put(41, "内涵质控");
        CONTENTID_TYPE.put(42, "形式质控");
        CONTENTID_TYPE.put(43, "内涵质控");
        CONTENTID_TYPE.put(44, "形式质控");
        CONTENTID_TYPE.put(45, "内涵质控");
        CONTENTID_TYPE.put(46, "形式质控");
        CONTENTID_TYPE.put(47, "形式质控");
        CONTENTID_TYPE.put(48, "内涵质控");
        CONTENTID_TYPE.put(49, "内涵质控");
        CONTENTID_TYPE.put(50, "内涵质控");
        CONTENTID_TYPE.put(51, "内涵质控");
        CONTENTID_TYPE.put(52, "内涵质控");
    }

    /**
     * 根据contentID 获得 对应 type (内涵质控OR形式质控)
     *
     * @param contentID contentID 1,2,3...
     * @return type
     */
    public static String getTypeByContentID(int contentID) {
        return CONTENTID_TYPE.getOrDefault(contentID, "未知质控");
    }

    /**
     * 将密码进行md5加密
     * @param password
     * @return
     */
    public static String getPassword(String password) {
        return StringUtil.isEmpty(password) ? "" : MD5.encode("pwd" + password + "|" + password.length());
    }

    /**\
     * 将'全部'转换为null，将字符数字转换为数字
     * @param source
     * @return
     */
    public static Integer stringToInteger(String source) {
        String value = source.trim();
        if (source==null||"".equals(value) || "全部".equals(source)) {
            return null;
        }
        if (source.matches("^-?\\d+$")) {
            return Integer.parseInt(source);
        } else {
            throw new IllegalArgumentException("Invalid Integer value '" + source + "'");
        }
    }

    /**
     * 根据正则获取第一个匹配对象的内容
     * String url = "http://172.12.11.123/test.txt";
     *  String regex = "(\\d+1)";
     *  结果为 11
     * @param regex
     * @param source
     * @return
     */
    public static String getMatcher(String regex, String source) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        if(matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    /**
     * 整个字符序列是否只包含正则匹配的字符
     * @param regex
     * @param source
     * @return true:只包含正则匹配的字符，false：含有其他字符
     */
    public static boolean contains(String regex, String source){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        return matcher.matches();
    }

}
