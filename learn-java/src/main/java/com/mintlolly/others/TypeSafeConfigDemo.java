package com.mintlolly.others;

import com.typesafe.config.ConfigFactory;

/**
 * Create by on jiangbo 2020/7/30 13:18
 * <p>
 * Description:
 */

public class TypeSafeConfigDemo {
    public static void main(String[] args) {
        String config = ConfigFactory.parseResources("./application.conf").root().render();
        System.out.println(config);

        System.out.println("========================================================================");
        String conf = ConfigFactory.parseString("{\n" +
                "      type     = \"delimited-text\",\n" +
                "      format   = \"CSV\",\n" +
                "      fields = [\n" +
                "      { name = \"phrase\", transform = \"concatenate($1, $2)\" },\n" +
                "      { name = \"dtg\",    transform = \"dateHourMinuteSecondMillis($3)\" },\n" +
                "      { name = \"lat\",    transform = \"$4::double\" },\n" +
                "      { name = \"lon\",    transform = \"$5::double\" },\n" +
                "      { name = \"geom\",   transform = \"point($lon, $lat)\" }\n" +
                "      ]\n" +
                "      user-data = {\n" +
                "        // note: keys will be treated as strings and should not be quoted\n" +
                "        my.user.key = \"$phrase\"\n" +
                "      }\n" +
                "    }").root().render();

        System.out.println(conf);
    }
}
