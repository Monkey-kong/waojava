package jsonpath;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class JsonPathTest {

    public static final String json = """
                {
                    "store": {
                        "book": [
                            {
                                "category": "reference",
                                "author": "Nigel Rees",
                                "title": "Sayings of the Century",
                                "price": 8.95
                            },
                            {
                                "category": "fiction",
                                "author": "Evelyn Waugh",
                                "title": "Sword of Honour",
                                "price": 12.99
                            },
                            {
                                "category": "fiction",
                                "author": "Herman Melville",
                                "title": "Moby Dick",
                                "isbn": "0-553-21311-3",
                                "price": 8.99
                            },
                            {
                                "category": "fiction",
                                "author": "J. R. R. Tolkien",
                                "title": "The Lord of the Rings",
                                "isbn": "0-395-19395-8",
                                "price": 22.99
                            }
                        ],
                        "bicycle": {
                            "color": "red",
                            "price": 19.95
                        }
                    },
                    "expensive": 10
                }""";
    
    @Before
    public void before() {
        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new JacksonJsonProvider();
            private final MappingProvider mappingProvider = new JacksonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });
    }
    
    @Test
    public void test() {
        List<String> authors = JsonPath.read(json, "$.store.book[*].author");
        System.out.println(authors);

        // 预编译 JsonPath，防止每次都编译
        JsonPath compile = JsonPath.compile("$.store.book[*].author");
        List<String> authors2 = compile.read(json);
        List<String> authors3 = compile.read(json);
        System.out.println(authors2);
        System.out.println(authors3);
        
        // 预编译 json，防止每次都解析 json
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        String author0 = JsonPath.read(document, "$.store.book[0].author");
        String author1 = JsonPath.read(document, "$.store.book[1].author");
        log.info("author0: {}, author1: {}", author0, author1);

        // 直接序列化为对象
        Book book = JsonPath.parse(json).read("$.store.book[0]", Book.class);
        log.info("book: {}", book);

        // 获取完整的泛型类型信息，请使用 TypeRef
        TypeRef<List<String>> typeRef = new TypeRef<>() {};
        List<String> titles = JsonPath.parse(json).read("$.store.book[*].title", typeRef);
        log.info("titles: {}", titles);

        // 找出价格小于10且分类为reference的图书
        List<Book> books =  JsonPath.parse(json).read("$.store.book[?(@.price < 10 && @.category == 'reference')]", new TypeRef<>() {});
        log.info("books: {}", books);
    }
    
    public static final String json2 = """
    {
        "result": {
            "hits": [
                {
                    "consignor_province": "广东省",
                    "consignor_city": "深圳市",
                    "is_employee": "是",
                    "is_wyj_user": "1",
                    "send_cmm_third_class_sd": [
                        "散单",
                        "其它零食",
                        "五谷杂粮",
                        "其它文具",
                        "粽子"
                    ],
                    "send_cmm_first_class_sd": [
                        "副食干货类",
                        "3c产品",
                        "文体娱乐",
                        "家居用品",
                        "null"
                    ]
                }
            ]
        }
    }""";
    
    @Test
    public void test2() {
        // 灰度-用户画像匹配
        JsonPath path = JsonPath.compile("$.result.hits[0][?(@.consignor_city in ['深圳市','成都市'])]");
        Object read = path.read(json2);
        log.info("read: {}", read);
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Book {
        private String category;
        private String author;
        private String title;
        private double price;
    }
}
