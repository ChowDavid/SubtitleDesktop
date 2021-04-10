package com.david.church;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SubTitleDesktop {
    /**
     * PingFang HK Regular 64 size
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("SubTile Application");
        File outputFile = new File("outputLine.txt");
        File headFile = new File("head.txt");
        File metaFile = new File("meta.txt");
        System.out.println("Subtitle file loading...");
        List<SubTitleDto> subtitles = readFile(new File("subTitle.txt"));
        subtitles.stream().forEach((dto)-> System.out.println(dto.toString()));

        Scanner keyboard = new Scanner(System.in);
        int input = 0;
        while(true){
            try {
                input = Integer.parseInt(keyboard.nextLine());
            } catch (Exception e){
                input++;
            }
            final int inputLine = input;
            Optional<SubTitleDto> output = subtitles.stream().filter((s)->s.getId()==inputLine).findAny();
            if (output.isPresent()){
                if (output.get().getSubtitle()!=null){
                    FileUtils.writeStringToFile(outputFile,output.get().getSubtitle(),"UTF-8",true);
                    FileUtils.writeStringToFile(outputFile,"\n","UTF-8",true);
                } else {
                    FileUtils.writeStringToFile(outputFile,"\n","UTF-8",true);
                }
                if (output.get().getHead()!=null){
                    FileUtils.writeStringToFile(headFile,output.get().getHead(),"UTF-8");
                } else {
                    FileUtils.writeStringToFile(headFile,"","UTF-8");
                }
                if (output.get().getMeta()!=null){
                    FileUtils.writeStringToFile(metaFile,output.get().getMeta(),"UTF-8");
                } else {
                    FileUtils.writeStringToFile(metaFile,"","UTF-8");
                }
                System.out.println(output.get().toString());
            } else {
                FileUtils.writeStringToFile(outputFile,"","UTF-8",true);
                FileUtils.writeStringToFile(headFile,"","UTF-8");
                FileUtils.writeStringToFile(metaFile,"","UTF-8");
                System.out.println("");
            }


        }

    }



    private static List<SubTitleDto> readFile(File file) throws IOException {
       return  FileUtils.readLines(file,"UTF-8").stream()
               .filter(l-> !l.startsWith("---"))
               .filter(l-> !l.startsWith("***"))
               .filter((l)->l.trim().length()!=0)
               .map((l)->new SubTitleDto(l)).collect(Collectors.toList());
    }
}
