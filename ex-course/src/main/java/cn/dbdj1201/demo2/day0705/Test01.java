package cn.dbdj1201.demo2.day0705;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author yz1201
 * @date 2020-07-05 16:00
 **/
@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File(Objects.requireNonNull(Test01.class.getClassLoader()
                .getResource("c3p0-config.xml")).getPath()), String.valueOf(StandardCharsets.UTF_8));
        log.debug("{}",document.getElementsByTag("property"));

    }
}
