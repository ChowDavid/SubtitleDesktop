package com.david.church;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubTitleDto {
    private int id;
    private String subtitle;
    private String head;
    private String meta;

    public SubTitleDto(String line) {
        String[] cells = line.split("\\|");
        id = Integer.parseInt(cells[0]);
        if (cells.length==3){
            if (cells[1].equalsIgnoreCase("h")){
                head=cells[2];
            } else if (cells[1].equalsIgnoreCase("m")){
                meta=cells[2];
            }
        } else {
            subtitle = cells[1];
        }
    }
}
