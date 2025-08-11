import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName

class MainKtTest {

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
      assertThat(result).contains("1번 명언이 등록되었습니다.")
   }
}