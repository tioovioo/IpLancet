package com.lancet.iplancet.testpackage;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @author yongjia.guo
 * @Description: 测试类.
 */
@Slf4j
public class TestClass {


    @Test
    public void testTemplate() {


    }


    @Test
    public void t01() {
        try {

            log.info("开始测试.........");
//            double result = 1 / 0;

        } catch (Exception e) {
            log.error("错误信息: {}.", e.toString());
            log.info("========================================");
            e.printStackTrace();


//            log.info("=======用户: {} 修改密码【报错】========",getUserId());
//            log.error("错误信息: {}.",e.toString());

        }

    }


    @Test
    public void t02() {

        Week friday = Week.FRIDAY;
        System.out.println(friday);


    }


    @Test
    public void test006() {

        //提交答案:
//        String submitAnswer ="初步诊断：急性胃肠炎@@，患者发热38.4°C，";
//        String submitAnswer ="手术@@前@@诊断：@@1.@@肠梗阻@@：@@肠系膜血栓@@？@@肠坏死@@？";
        //采纳答案:
//        String adoptionAnswer ="初步@@诊断@@：@@急性@@胃肠@@炎@@，@@患者@@发热@@38.4@@℃@@，";
//        String adoptionAnswer ="手术前诊断：1.肠梗阻：@@肠系膜血栓？肠坏死？";

        String submitAnswer = "初步诊断：急性胃肠炎@@，患者发热@@38.4°C@@，";
        //采纳答案:
        String adoptionAnswer = "初步@@诊断：急@@性胃肠炎@@，患者@@发热@@38.4°C@@，";

        String[] userAnswerArr = submitAnswer.split("@@");
        String[] adoptionAnswerArr = adoptionAnswer.split("@@");

        //正确答案(高亮状态).
        Set<WordTopicDtail> userAnswerSet = checkUserAnswer(adoptionAnswerArr, userAnswerArr);
        //转为List并排序
        List<WordTopicDtail> userAnswerList = new ArrayList<>(userAnswerSet);
        userAnswerList.sort(Comparator.comparingInt(WordTopicDtail::getIndex));

        //用户提交答案(高亮状态)
        Set<WordTopicDtail> finalUserAnswerSet = checkUserAnswer(userAnswerArr, adoptionAnswerArr);
        //转为List并排序
        List<WordTopicDtail> finalUserAnswerList = new ArrayList<>(finalUserAnswerSet);
        finalUserAnswerList.sort(Comparator.comparingInt(WordTopicDtail::getIndex));


        log.info("=========标准答案==========");
        finalUserAnswerList.forEach(System.out::println);

        log.info("=========用户提交答案==========");
        userAnswerList.forEach(System.out::println);

    }


    @Test
    public void test007() {
        //情景模拟:
        //TODO: 全部相同:(state全部为:0) √
//        log.info("===========当前场景【全部相同】===============");
//        //提交答案:
//        String submitAnswer = "初步诊断：急性胃肠炎@@，患者@@发热38.4°C，";
//        //采纳答案:
//        String adoptionAnswer = "初步诊断：急性胃肠炎@@，患者@@发热38.4°C，";

        //TODO: 全部不相同:(state全部为:1) 对
//        log.info("===========当前场景【全部不相同】===============");
//        提交答案:
//        String submitAnswer = "初步@@诊断：急性胃@@肠炎@@，患@@者@@发热@@38.4°C，";
////        采纳答案:
//        String adoptionAnswer = "初步诊断：急性胃肠炎@@，患者@@发热38.4°C，";
        //TODO: us的长度>fs长度:
//        log.info("===========当前场景【us的长度>fs长度:】===============");
        //提交答案:
//        String submitAnswer = "初@@步诊断：急性胃@@肠炎@@，患@@者@@发热38.4°C，";
//        //采纳答案:
//        String adoptionAnswer = "初步诊断：急性胃肠@@炎@@，患者@@发热38.4°C，";
        //TODO: us的长度<fs长度:
        log.info("===========当前场景【us的长度<fs长度:】===============");
        //提交答案:
        String submitAnswer = "初步诊断：急性胃肠炎@@，患者@@发热38.4°C，";
        //采纳答案:
        String adoptionAnswer = "初@@步诊@@断：急性胃@@肠炎@@，患者@@发@@热3@@8.4@@°C，";
        //TODO: us的长度=fs长度,但是分隔符位置不同.
        //提交答案:
//        String submitAnswer = "初步诊断：急性胃肠炎@@，患者@@发热38.4°C，";
        //采纳答案:
//        String adoptionAnswer = "初步诊断：急性胃肠炎@@，患者@@发热38.4°C，";

        String[] userAnswerArr = submitAnswer.split("@@");
        String[] adoptionAnswerArr = adoptionAnswer.split("@@");

        List<WordTopicDtail> userAnswerList = new ArrayList();
        List<WordTopicDtail> finalUserAnswerList = new ArrayList();
        compareAnswer(adoptionAnswerArr, userAnswerArr, userAnswerList, finalUserAnswerList);
        log.info("=========标准答案==========");
        finalUserAnswerList.forEach(System.out::println);

        log.info("=========用户提交答案==========");
        userAnswerList.forEach(System.out::println);

    }


    /**
     * @param fs    标准答案
     * @param us    用户答案
     * @param uList 用户List
     * @param fList 标准List
     */
    private static void compareAnswer(String[] fs, String[] us, List<WordTopicDtail> uList, List<WordTopicDtail> fList) {
        //标准答案总索引
        int fIndex = 0;
        //用户答案总索引
        int uIndex = 0;
        //key->index value->WordTopicDtail
        Map<Integer, WordTopicDtail> fMap = new LinkedHashMap();
        Map<Integer, WordTopicDtail> uMap = new LinkedHashMap();

        //用户答案的当前索引.
        int uCurrentIndex = 0;

        //避免重复添加数据的标识:
        boolean flag = true;
        boolean secondFlag = true;
        boolean increaseFlag = true;

        for (int i = 0; i < fs.length; i++) {
            String f = fs[i];
            fIndex += f.length();

            for (int j = uCurrentIndex; j < us.length; j++) {
                String u = us[j];
                if (increaseFlag) {
                    uIndex += u.length();
                }

                //当前遍历总长度相同.
                if (fIndex == uIndex) {
                    //两个子字符串相同.
                    if (f.length() == u.length()) {
                        WordTopicDtail answerDetail = new WordTopicDtail(u, 0, j);
                        uMap.put(j, answerDetail);
                        answerDetail = new WordTopicDtail(f, 0, i);
                        fMap.put(i, answerDetail);

                    } else if (f.length() < u.length()) {
                        WordTopicDtail answerDetail = new WordTopicDtail(f, 1, i);
                        fMap.put(i, answerDetail);
                    } else {
                        WordTopicDtail answerDetail = new WordTopicDtail(u, 1, j);
                        uMap.put(j, answerDetail);
                    }

                    fIndex = 0;
                    uIndex = 0;
                    flag = true;
                    secondFlag = true;
                    uCurrentIndex++;
                    increaseFlag = true;
                    break;
                } else if (fIndex > uIndex) {
                    WordTopicDtail answerDetail = new WordTopicDtail(u, 1, j);
                    uMap.put(j, answerDetail);
                    uCurrentIndex++;
                    if (flag) {
                        answerDetail = new WordTopicDtail(f, 1, i);
                        fMap.put(i, answerDetail);
                        flag = false;
                    }
                    secondFlag = true;
                    increaseFlag = true;
                } else {
                    WordTopicDtail answerDetail = new WordTopicDtail(f, 1, i);
                    fMap.put(i, answerDetail);
                    if (secondFlag) {
                        answerDetail = new WordTopicDtail(u, 1, j);
                        uMap.put(j, answerDetail);
                        secondFlag = false;
                    }
                    flag = true;
                    increaseFlag = false;
                    break;
                }
            }
        }
        //封装返回值:
        uMap.forEach((k, v) -> uList.add(v));
        fMap.forEach((k, v) -> fList.add(v));

    }


    /**
     * 核对分词语料用户提交的答案正确性
     *
     * @param oks 系统采纳的答案集合
     * @param ngs 用户提交的答案集合
     * @return
     */
    private static Set<WordTopicDtail> checkUserAnswer(String[] oks, String[] ngs) {
        Set<WordTopicDtail> result = new HashSet<>();
        List<Map<String, Integer>> list = new ArrayList<>();
        int oksIndex = 0;
        int ngsIndex = 0;

        for (int i = 0; i < oks.length; i++) {
            String ok = oks[i];
            oksIndex += ok.length();
            int x = 0;
            int y = 0;
            if (list.size() > 0) {
                x = list.get(list.size() - 1).get("ngIndex");
                y = list.get(list.size() - 1).get("okIndex");
            }
            for (int j = x; j < ngs.length; j++) {
                String ng = ngs[j];
                ngsIndex += ng.length();
                if (oksIndex == ngsIndex) {
                    oksIndex = 0;
                    ngsIndex = 0;
                    int state = 1;
                    if (j == x && i == y) {
                        state = 0;
                    }
                    WordTopicDtail answerDetail = new WordTopicDtail();
                    answerDetail.setState(state);
                    answerDetail.setName(ng);
                    answerDetail.setIndex(j);
                    result.add(answerDetail);

                    Map<String, Integer> map = new HashMap<>();
                    map.put("okIndex", i + 1);
                    map.put("ngIndex", j + 1);
                    list.add(map);

                    break;

                } else if (oksIndex < ngsIndex) {
                    ngsIndex = 0;
//                    break;
                } else {
                    WordTopicDtail answerDetail = new WordTopicDtail();
                    answerDetail.setState(1);
                    answerDetail.setName(ng);
                    answerDetail.setIndex(j);
                    result.add(answerDetail);
                }
            }
        }
        return result;
    }


}
