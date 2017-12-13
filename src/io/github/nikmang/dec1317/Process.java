package io.github.nikmang.dec1317;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik on 12/13/2017.
 */
public class Process {
    List<Process> children;
    String name;

    int[] allocation;
    int[] needed;

    Process(String name, int[] allocation, int[] needed) {
        assert allocation.length == 3 && needed.length == 3;

        this.name = name;

        this.allocation = allocation;
        this.needed = needed;

        this.children = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return name.hashCode(); //assuming names are unique
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Process))
            return false;

        Process p = (Process) o;

        return p.name.equals(this.name);
    }
}
