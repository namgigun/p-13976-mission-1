import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import java.io.File

class AppTest {

    // 테스트 실행후, 모든 테스트 파일 삭제
//    @AfterEach
//    fun cleanUp() {
//        val dir = File("db/wiseSaying")
//        if (dir.exists()) {
//            dir.deleteRecursively()
//        }
//    }

   @Test
   @DisplayName("종료")
   fun t1() {
      val result = TestRunner.run("")

      assertThat(result).contains("프로그램을 종료합니다")
   }

   @Test
   @DisplayName("등록")
   fun t2() {
      val result = TestRunner.run("""
         등록
         현재를 사랑하라
         작자미상
         종료
      """)

      assertThat(result).contains("명언 :")
      assertThat(result).contains("작가 :")
   }

   @Test
   @DisplayName("등록시 생성된 명언번호 노출")
   fun t3() {
      val result = TestRunner.run("""
         등록
         현재를 사랑하라
         작자미상
         종료
      """)

      assertThat(result).contains("명언 :")
      assertThat(result).contains("작가 :")
      assertThat(result).contains("1번 명언이 등록되었습니다.")
   }

   @Test
   @DisplayName("등록할때 마다 생성되는 명언번호가 증가")
   fun t4() {
       val result = TestRunner.run("""
         등록
         현재를 사랑하라
         작자미상
         등록
         현재를 사랑하라
         작자미상
         종료
      """)

      assertThat(result).contains("명언 :")
      assertThat(result).contains("작가 :")
      assertThat(result).contains("1번 명언이 등록되었습니다.")
      assertThat(result).contains("2번 명언이 등록되었습니다.")
   }

    @Test
    @DisplayName("목록")
    fun t5() {
        val result = TestRunner.run("""
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            목록
            종료
        """)

        assertThat(result).contains("2 / 작자미상 / 과거에 집착하지 마라.")
        assertThat(result).contains("1 / 작자미상 / 현재를 사랑하라.")
    }

    @Test
    @DisplayName("1번 명언삭제")
    fun t6() {
        val result = TestRunner.run("""
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            목록
            삭제?id=1
            종료
        """)

        assertThat(result).contains("1번 명언이 삭제되었습니다.")
    }

    @Test
    @DisplayName("존재하지 않는 명언삭제에 대한 예외처리")
    fun t7() {
        val result = TestRunner.run("""
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            목록
            삭제?id=1
            삭제?id=1
            종료
        """)

        assertThat(result).contains("1번 명언이 삭제되었습니다.")
        assertThat(result).contains("1번 명언은 존재하지 않습니다.")
    }

    @Test
    @DisplayName("명언수정")
    fun t8() {
        val result = TestRunner.run("""
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            목록
            삭제?id=1
            삭제?id=1
            수정?id=3
            수정?id=2
            현재와 자신을 사랑하라.
            홍길동
            목록
            종료
        """)

        assertThat(result).contains("2 / 홍길동 / 현재와 자신을 사랑하라.")
    }

    @Test
    @DisplayName("검색")
    fun t9() {
        val result = TestRunner.run("""
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            목록
            목록?keywordType=content&keyword=과거
            목록?keywordType=author&keyword=작자
            종료
        """)

        assertThat(result).contains("검색타입 : content")
        assertThat(result).contains("검색어 : 과거")
        assertThat(result).contains("2 / 작자미상 / 과거에 집착하지 마라.")

        assertThat(result).contains("검색타입 : author")
        assertThat(result).contains("검색어 : 작자")
        assertThat(result).contains("2 / 작자미상 / 과거에 집착하지 마라.")
        assertThat(result).contains("1 / 작자미상 / 현재를 사랑하라.")
    }

    @Test
    @DisplayName("페이징")
    fun t10() {
        val result = TestRunner.run("""
            등록
            명언1
            작자미상1
            등록
            명언2
            작자미상2
            등록
            명언3
            작자미상3
            등록
            명언4
            작자미상4
            등록
            명언5
            작자미상5
            등록
            명언6
            작자미상6
            등록
            명언7
            작자미상7
            등록
            명언8
            작자미상8
            등록
            명언9
            작자미상9
            등록
            명언10
            작자미상10
            목록?id=2
            종료
        """)

//        assertThat(result).contains("""
//            10 / 작자미상 10 / 명언 10
//            9 / 작자미상 9 / 명언 9
//            8 / 작자미상 8 / 명언 8
//            7 / 작자미상 7 / 명언 7
//            6 / 작자미상 6 / 명언 6
//        """.trimIndent())
//        assertThat(result).contains("페이지 : [1] / 2")

        assertThat(result).contains("""
            5 / 작자미상 5 / 명언 5
            4 / 작자미상 4 / 명언 4
            3 / 작자미상 3 / 명언 3
            2 / 작자미상 2 / 명언 2
            1 / 작자미상 1 / 명언 1
        """.trimIndent())
        assertThat(result).contains("페이지 : 1 / [2]")
    }

}