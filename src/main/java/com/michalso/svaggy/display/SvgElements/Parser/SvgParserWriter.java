package com.michalso.svaggy.display.SvgElements.Parser;

import com.michalso.svaggy.display.SvgElements.Basic.SvgRoot;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface SvgParserWriter {

    SvgRoot parse(String svgString) throws ParserConfigurationException, IOException, SAXException;
}
