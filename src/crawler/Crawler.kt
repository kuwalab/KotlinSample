package crawler

import org.jsoup.Jsoup
import java.util.ArrayList
import java.util.HashMap
import com.fasterxml.jackson.databind.ObjectMapper

fun sleep() {
    Thread.sleep(3000)
}

val URL_SERVER = "http://www.city.hakusan.ishikawa.jp"
val dayMap = hashMapOf(
        "日" to 1,
        "月" to 2,
        "火" to 3,
        "水" to 4,
        "木" to 5,
        "金" to 6,
        "土" to 7
)

fun main(args: Array<String>) {
    var doc = Jsoup.connect("${URL_SERVER}/k/shiminseikatsubu/kankyo/4r/gomi-k01.html").get()
    sleep()

    var chiikiArray = doc.select("a")
    // とりあえず松任のみ処理
    var chiikiUrl = chiikiArray[0].attr("href")


    // あ行〜
    var kanaGyouUrlArray = Jsoup.connect(URL_SERVER + chiikiUrl).get().select("a")
    sleep()

    var trashUrlMap = HashMap<String, Int>()
    var chouList = ArrayList<Chou>()

    // あ行、か行・・・
    for (kanaGyouUrl in kanaGyouUrlArray) {
        if (kanaGyouUrl.text() == "戻る") continue

        var kanaHtml = Jsoup.connect(URL_SERVER + kanaGyouUrl.attr("href")).get()
        sleep()

        var chouUrlArray = kanaHtml.select("a")

        // 八ツ矢町、八ツ矢新町・・・
        var trashNo = 0
        for (chouUrl in chouUrlArray) {
            if (chouUrl.text() == "戻る") continue

            var link = chouUrl.attr("href")
            var chouName = chouUrl.text()

            if (!trashUrlMap.containsKey(link)) {
                trashUrlMap.put(link, trashNo)
                trashNo++
            }
            chouList.add(Chou(chouName, trashUrlMap.get(link)))
        }
    }

    var trashTableMap = HashMap<Int, ArrayList<TrashTable>>()
    // trashUrlMapの処理
    for ((key, value) in trashUrlMap) {
        var trashTableList = ArrayList<TrashTable>()
        trashTableMap.put(value, trashTableList)

        var monthHtml = Jsoup.connect(URL_SERVER + key).get()
        var monthUrlArray = monthHtml.select("a")
        var index = 0
        for (monthUrl in monthUrlArray) {
            if (index == 12) break
            index++
            var trashTable = TrashTable(ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
            trashTableList.add(trashTable)

            var trashData = Jsoup.connect(URL_SERVER + monthUrl.attr("href")).get()
            sleep()

            var data = trashData.text().replaceAll("　", "") // 全角ブランクを潰す
            data = data.replaceAll(" ", "") // 半角ブランクを潰す
            println(data)
            // 一般ごみ
            var splits = data.split("一般ごみ毎週")[1].split("燃やす粗大ごみ")[0].split("・")
            // 2つ固定で処理する
            trashTable.nomalDay.add(dayMap.get(splits[0]))
            trashTable.nomalDay.add(dayMap.get(splits[1]))
            // 燃やす粗大ごみ
            var num = data.split("燃やす粗大ごみ")[1].split("日")[0]
            trashTable.sodaiDate.add(Integer.parseInt(num))
            // 燃えないごみ
            num = data.split("燃えないごみ")[1].split("日")[0]
            trashTable.moenaiDate.add(Integer.parseInt(num))
            // 資源ごみ1
            num = data.split("ボトル・容器包装プラ）")[1].split("日")[0]
            trashTable.shigen1Date.add(Integer.parseInt(num))
            // 資源ごみ2
            num = data.split("資源ごみ（びん・缶・容器包装プラ）")[1].split("日")[0]
            trashTable.shigen2Date.add(Integer.parseInt(num))
        }
    }

    var mapper = ObjectMapper()
    var json = mapper.writeValueAsString(trashTableMap)
    println(json)

    json = mapper.writeValueAsString(chouList)
    println(json)
}
