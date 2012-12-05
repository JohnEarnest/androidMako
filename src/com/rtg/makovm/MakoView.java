package com.rtg.makovm;

import java.io.*;

import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

import android.graphics.*;

public class MakoView extends View {

	MakoVM vm;

	public MakoView(Context c, AttributeSet a) {
		super(c, a);
		try {
			int[] rom = loadRom(c.getAssets().open("Loko.rom"), null);
			vm = new MakoVM(rom);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		vm.sync();
	}

	private static int[] loadRom(InputStream i, int[] prev) {
		try {
			DataInputStream in = new DataInputStream(i);
			int[] rom = new int[in.available() / 4];
			for(int x = 0; x < rom.length; x++) {
				rom[x] = in.readInt();
			}
			in.close();
			System.out.println("Restored from save file!");
			return rom;
		}
		catch(IOException ioe) {
			System.out.println("Unable to load rom!");
			return prev;
		}
	}

	@Override
	public void onDraw(Canvas c) {
		c.drawBitmap(vm.p, 0, 0, 0, 0, 320, 240, false, null);
	}
}