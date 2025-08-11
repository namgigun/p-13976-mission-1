class App {
    val wiseSayings = mutableListOf<WiseSaying>()
    var lastId = 1
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

                wiseSayings.add(WiseSaying(lastId++, sentence, writer))
                println("${wiseSayings.size}번 명언이 등록되었습니다.")
            }

            else if(input.equals("목록")) {
                println("번호 / 작가 / 명언")
                println("----------------------")
                for (wiseSaying in wiseSayings.reversed()) {
                    println("${wiseSaying.id} / ${wiseSaying.writer} / ${wiseSaying.sentence}")
                }
            }
        }
    }
}