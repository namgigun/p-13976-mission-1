class App {
    val wiseSayings = mutableListOf<WiseSaying>()
    fun run() {
        println("== 명언 앱 ==")

        while(true) {
            print("명령) ")

            val input = readlnOrNull()!!.trim()

            if(input.equals("종료")) {
                println("프로그램을 종료합니다.");
                break
            }

            else if(input.equals("등록")) {
                print("명언 : ")
                val sentence = readln()
                print("작가 : ")
                val writer = readln()

                wiseSayings.add(WiseSaying(sentence, writer))
                println("${wiseSayings.size}번 명언이 등록되었습니다.")
            }
        }
    }
}