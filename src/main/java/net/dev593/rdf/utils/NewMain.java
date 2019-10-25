/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dev593.rdf.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import org.apache.commons.io.IOUtils;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

/**
 *
 * @author cedia
 */
public class NewMain {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException {

    /**
     * args
     *
     * 0: Input file (nquas) 1: Valid triples file 2: Invalid triples file 3:
     * baseURI
     */
    FileWriter validFile = new FileWriter(args[1]);
    PrintWriter validWriter = new PrintWriter(validFile);
    FileWriter invalidFile = new FileWriter(args[2]);
    PrintWriter invalidWriter = new PrintWriter(invalidFile);
    BufferedReader inputReader;
    try {
      inputReader = new BufferedReader(new FileReader(args[0]));
      int i = 0;
      String line = inputReader.readLine();
      while (line != null) {
        i++;
        if (i % 100000 == 0) {
          System.out.println("log: " + i);
        }
        // read next line
        try {
          InputStream toInputStream = IOUtils.toInputStream(line);
          Rio.parse(toInputStream, args[3], RDFFormat.NQUADS);
          validWriter.println(line);
        } catch (Exception e) {
          System.err.println(line);
          invalidWriter.println(line);
        }
        line = inputReader.readLine();
      }
      validWriter.close();
      invalidWriter.close();
      inputReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
