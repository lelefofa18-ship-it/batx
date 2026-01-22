package eu.kanade.tachiyomi.extension.all.xbat

import eu.kanade.tachiyomi.source.online.ParsedHttpSource
import eu.kanade.tachiyomi.source.model.*
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class XBat : ParsedHttpSource() {

    override val name = "XBAT.IO"
    override val baseUrl = "https://xbat.io"
    override val lang = "all"
    override val supportsLatest = true

    // ===== POPULAR =====
    override fun popularMangaSelector() = "a[href^=/title/]"

    override fun popularMangaFromElement(element: Element): SManga =
        SManga.create().apply {
            title = element.text()
            url = element.attr("href")
        }

    override fun popularMangaNextPageSelector(): String? = null

    // ===== DETALHES =====
    override fun mangaDetailsParse(document: Document): SManga =
        SManga.create().apply {
            description =
                document.select("meta[name=description]").attr("content")
            thumbnail_url =
                document.select("meta[property=og:image]").attr("content")
        }

    // ===== CAPÍTULOS =====
    override fun chapterListSelector() = "a[href*=-ch_]"

    override fun chapterFromElement(element: Element): SChapter =
        SChapter.create().apply {
            name = element.text()
            url = element.attr("href")
        }

    // ===== PÁGINAS =====
    override fun pageListParse(document: Document): List<Page> =
        document.select("img.w-full.h-full").mapIndexed { index, img ->
            Page(
                index,
                "",
                img.attr("abs:src")
            )
        }

    override fun imageUrlParse(document: Document) = ""
}
