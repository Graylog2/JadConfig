package com.github.joschi.jadconfig.documentation.printers;

import java.io.Closeable;
import java.io.Flushable;
import java.util.List;

public interface DocsPrinter extends Flushable, Closeable {

    void write(List<ConfigurationSection> sections);
}
