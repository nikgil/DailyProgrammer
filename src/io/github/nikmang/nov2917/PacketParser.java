package io.github.nikmang.nov2917;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: fvandepitte
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/72ivih/20170926_challenge_333_easy_packet_assembler/?st=jandrmnd&sh=7e8db049
 * Difficulty: Easy
 *
 * Test Cases:
 * Found in separate file
 */
public class PacketParser {
    Map<Integer, Packet> packetData;
    List<String> lines;

    PacketParser() throws IOException, URISyntaxException {
        lines = Files.readAllLines(Paths.get(PacketParser.class.getResource("Input.txt").toURI()));
        packetData = new HashMap<>();
    }

    void parse() {
        for (String s : lines) {
            String[] arr = s.split("\\s{4}"); //Will have 2 parts
            String[] subArr = arr[1].split("\\s{2,3}"); //Will have 3 parts

            int msgID = Integer.parseInt(arr[0]);
            int countID = Integer.parseInt(subArr[1]);
            Packet target;

            if(packetData.containsKey(msgID)) {
                target = packetData.get(msgID);
            } else {
                target = new Packet(countID);
                packetData.put(msgID, target);
            }

            String arg = subArr.length == 3 ? subArr[2] : "";
            target.msgs.put(Integer.parseInt(subArr[0]), arg);
        }
    }

    void print() {
        for(Map.Entry<Integer, Packet> entry : packetData.entrySet()) {
            Packet p = entry.getValue();

            for(Map.Entry<Integer, String> data : p.msgs.entrySet()) {
                System.out.printf("%4d\t%2d\t%2d\t%s\n", entry.getKey(), data.getKey(), p.countID, data.getValue());
            }
        }
    }

    private class Packet {
        int countID;
        Map<Integer, String> msgs;

        Packet(int countID) {
            this.countID = countID;
            this.msgs = new HashMap<>();
        }
    }
}
