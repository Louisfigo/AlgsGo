package net.louis.algs;

import net.louis.collection.linked.LinkedQueue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PrintFileTree {

    private final static String TAB = "\t";
    private final static String ENTER = "\n";

    private LinkedQueue<String> fileNames = new LinkedQueue<>();

    public static void main(String args[])
    {
        Path dir = Paths.get("/Users/zhangweiguang/IdeaProjects/AlgsGo/src");
        PrintFileTree printFileTree = new PrintFileTree();
        printFileTree.putNameIntoQueue("",dir);

        while(!printFileTree.fileNames.isEmpty())
        {
            System.out.println(printFileTree.fileNames.dequeue());
        }

    }

    public void putNameIntoQueue(String rootTab,Path rootPath)
    {
        String thisLevelTab = rootTab+TAB;
        fileNames.enqueue(thisLevelTab+rootPath.getFileName()+"\n");
        try(Stream<Path> stream = Files.list(rootPath))
        {
            stream.forEach(p->{
                if(Files.isRegularFile(p))
                {
                    fileNames.enqueue(thisLevelTab+TAB+p.getFileName()+"\n");
                }
                else
                {
                    putNameIntoQueue(thisLevelTab+TAB,p);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
