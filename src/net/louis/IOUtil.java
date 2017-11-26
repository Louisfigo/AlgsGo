package net.louis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IOUtil {

    public static  List<String> readLineFromFile(String fileName)
    {
        List<String> input = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(s->
            {
                String []oneLine = s.split(" ");

                for(String s1:oneLine)
                    input.add(s1);

            });

        } catch (IOException e) {
            e.printStackTrace();
        }



        return input;

    }
}
