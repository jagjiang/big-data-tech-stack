package samples

import com.mintlolly.application.WordCountApplication
import com.mintlolly.bean.JsonBean.WordCountBean
import com.mintlolly.util.JsonUtil
import org.junit.Test

/**
 * Create by on jiangbo 2021/1/4 9:20
 *
 * Description:wordCount测试类
 *
 */
class WordCountTest {

  @Test
  def dataAnalysisTest(): Unit ={
    implicit val formats = org.json4s.DefaultFormats
    val wordCountBean = WordCountBean("./data/test.txt", "data/output")
    val str = JsonUtil.CaseClass2Json(wordCountBean)
    WordCountApplication.main(Array(str))
  }
}
