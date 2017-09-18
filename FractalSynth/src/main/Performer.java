package main;

import csnd6.csnd6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import csnd6.Csound;
import csnd6.CsoundPerformanceThread;

public class Performer {

	@SuppressWarnings("resource")
	public static void render(String sco, String out) {
        csnd6.csoundInitialize(
                csnd6.CSOUNDINIT_NO_ATEXIT | csnd6.CSOUNDINIT_NO_SIGNAL_HANDLER);
        
        String orc = null;
        
		try {
			orc = new Scanner(new File("config/grainplot.orc")).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        Csound c = new Csound();
        c.SetOption("-o" + out);
        c.CompileOrc(orc);
        c.ReadScore(sco);
        c.Start();
        
        CsoundPerformanceThread t = new CsoundPerformanceThread(c);  
        t.Play();
        t.Join();
        
        c.Stop();
        c.Cleanup();

    }
}