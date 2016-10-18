/*
 * Created by SharpDevelop.
 * User: cdahmedeh
 * Date: 10/17/2016
 * Time: 19:09
 * 
 */

using System;
using System.Runtime.InteropServices;

namespace MuraleWinCommand {
	public static class User32 {
		[DllImport("user32.dll", SetLastError = true)]
		public static extern IntPtr FindWindow(string lpClassName, string lpWindowName);
		
		[DllImport("user32.dll", CharSet = CharSet.Auto)]
        public static extern int SendMessage(IntPtr hWnd, uint Msg, IntPtr wParam, IntPtr lParam);
	}
}