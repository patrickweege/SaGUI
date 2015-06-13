package com.fatuhiva.ext.common.render.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Stack;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class RenderWriter {

    private final Writer out;
    private int padding = 0;
    private final Stack<String> commaStack;
    private final Stack<String> trimStack;
    private boolean isNewLine = false;
    
    public RenderWriter(RenderWriter writer) {
        this.out = writer.out;
        this.commaStack = new Stack<String>();
        this.trimStack = new Stack<String>();
    }

    public RenderWriter(Writer writer, int padding) {
        this.out = writer;
        this.padding = padding;
        this.commaStack = new Stack<String>();
        this.trimStack = new Stack<String>();
    }

    public RenderWriter ln() throws IOException {
    	out.write("\n");
    	isNewLine = true;
        return this;
    }
    
    public RenderWriter commaLn() throws IOException {
        this.out.write("\n");
        this.isNewLine = true;
        this.pushComma();
        return this;
    }


    public RenderWriter ident() throws IOException {
        padding++;
        return this;
    }

    public RenderWriter udent() throws IOException {
        padding--;
        return this;
    }

    public RenderWriter tab() throws IOException {
    	//out.write(StringUtils.repeat("   ", padding));
        return this;
    }

    public RenderWriter tab(int tabSize) throws IOException {
    	//out.write(StringUtils.repeat("   ", padding + tabSize));
        return this;
    }

    public RenderWriter appendLn(String script) throws IOException {
    	this.doWrite(script);
        this.ln();
        return this;
    }

    public RenderWriter append(String script) throws IOException {
    	this.doWrite(script);
        return this;
    }
    
    public RenderWriter format(String format, Object... values) throws IOException {
    	this.doWrite(String.format(format, values));
        return this;
    }
    
    public RenderWriter formatLn(String format, Object... values) throws IOException {
    	this.doWrite(String.format(format, values));
    	this.ln();
        return this;
    }

    public RenderWriter writeConfigFmt(String prop, String format, Object... value) throws IOException {
        Object[] args = new Object[2];
        args[0] = prop;
        args[1] = String.format(format, value);
        this.doWrite(String.format("%1$s : %2$s", args));
        return this;
    }
    
    public RenderWriter writeConfig(String prop, Object value) throws IOException {
    	Object[] args = new Object[2];
    	args[0] = prop;
    	args[1] = value == null ? "" : value.toString();
    	this.doWrite(String.format("%1$s : %2$s", args));
        return this;
    }
    
    public RenderWriter writeConfigAsString(String prop, Object value) throws IOException {
    	Object[] args = new Object[2];
    	args[0] = prop;
    	args[1] = value == null ? "" : value.toString();
    	this.doWrite(String.format("%1$s : '%2$s'", args));
        return this;
    }
    
    public RenderWriter pushComma() {
    	this.commaStack.push(",");
    	return this;
    }
    
    public RenderWriter popComma() {
    	this.commaStack.pop();
    	return this;
    }

    public RenderWriter rTrim() {
        this.trimStack.push("RTRIM");
        return this;
    }
    
    public RenderWriter popTrim() {
        this.trimStack.pop();
        return this;
    }

    public int getPadding() {
        return padding;
    }
    
    private void doWrite(String toWrite) throws IOException {
        innerTab(1);
    	if(!commaStack.isEmpty()) {
        	out.write(commaStack.pop());
    	}
    	out.write(toWrite);
    }
    
    public RenderWriter innerTab(int tabSize) throws IOException {
        if(isNewLine) out.write(StringUtils.repeat("   ", padding + tabSize));
        isNewLine = false;
        return this;
    }


}
