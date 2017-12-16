package io.github.nikmang.dec1417;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Author: fvandepitte
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/5961a5/20161024_challenge_289_easy_its_super_effective/?st=jb8kizjb&sh=de2df96f
 * Difficulty: Easy
 *
 * Test Cases [Output]:
 * fire -> grass [2.0x]
 * fighting -> ice rock [4.0x]
 * psychic -> poison dark [0.0x]
 * water -> normal [1.0x]
 * fire -> rock [0.5x]
 */
public class PokeRequest {

    public static String getDamageType(String type1, String... types) throws IOException {
        double damage = 1.0;

        Connection conn = Jsoup.connect("http://pokeapi.co/api/v2/type/" + type1 + "/");
        conn.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
        conn.ignoreContentType(true);
        conn.method(Connection.Method.GET);

        JsonObject obj = new JsonParser().parse(conn.execute().body()).getAsJsonObject();
        JsonObject damages = obj.getAsJsonObject("damage_relations");

        JsonArray arr1 = damages.getAsJsonArray("half_damage_to");
        JsonArray arr2 = damages.getAsJsonArray("no_damage_to");
        JsonArray arr3 = damages.getAsJsonArray("double_damage_to");

        for (String type2 : types) {
            boolean isFound = false;

            for (JsonElement element : arr1) {
                if (element.getAsJsonObject().get("name").getAsString().equals(type2)) {
                    damage *= 0.5;
                    isFound = true;
                    break;
                }
            }

            if (isFound)
                continue;

            for (JsonElement element : arr2) {
                if (element.getAsJsonObject().get("name").getAsString().equals(type2)) {
                    damage *= 0;
                    isFound = true;
                    break;
                }
            }

            if (isFound)
                continue;

            for (JsonElement element : arr3) {
                if (element.getAsJsonObject().get("name").getAsString().equals(type2)) {
                    damage *= 2.0;
                    break;
                }
            }
        }

        return damage + "x";
    }
}
