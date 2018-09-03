/*
 * Created by SharpDevelop.
 * User: cdahmedeh
 * Date: 10/17/2016
 * Time: 12:19
 *
 */
using System;
using System.Runtime.InteropServices;
using System.Threading;
using CommandLine;

namespace MuraleWinCommand
{
	class Options {
		[Option('f', "file", Required = true, HelpText = "Full absolute path of wallpaper image file")]
		public string File { get; set; }
		
		[Option('s', "style", Required = true, HelpText = "Display style of Wallpaper. Possible values are {center, tile, stretch, keep-aspect, crop-to-fit, span}")]
		public string Style { get; set; }
		
		[ParserState]
		public IParserState LastParserState { get; set; }
	}
	
	class Program
	{
		[STAThread]
		public static void Main(string[] args)
		{
			Options options = new Options();
			bool valid = CommandLine.Parser.Default.ParseArgumentsStrict(args, options);
			
			if (valid) {
				IActiveDesktop activeDesktop = ActiveDesktop.Create();
				string filePath = options.File;
				string style = options.Style;
				
				WallPaperStyle wpStyle = WallPaperStyle.WPSTYLE_CROPTOFIT;
				
				if (style.Equals("center")) {
					wpStyle = WallPaperStyle.WPSTYLE_CENTER;
				} else if (style.Equals("tile")) {
					wpStyle = WallPaperStyle.WPSTYLE_TILE;
				} else if (style.Equals("stretch")) {
					wpStyle = WallPaperStyle.WPSTYLE_STRETCH;
				} else if (style.Equals("keep-aspect")) {
					wpStyle = WallPaperStyle.WPSTYLE_KEEPASPECT;
				} else if (style.Equals("crop-to-fit")) {
					wpStyle = WallPaperStyle.WPSTYLE_CROPTOFIT;
				} else if (style.Equals("span")) {
					wpStyle = WallPaperStyle.WPSTYLE_SPAN;
				}

				WALLPAPEROPT wallpaperOpt = new WALLPAPEROPT();
				wallpaperOpt.dwStyle = wpStyle;
				wallpaperOpt.SizeOf = Marshal.SizeOf(typeof(WALLPAPEROPT));

				IntPtr progmanWindow = User32.FindWindow("Progman", null);
				User32.SendMessage(progmanWindow, 0x52c, IntPtr.Zero, IntPtr.Zero);
				
				activeDesktop.SetWallpaper(filePath, 0);
				activeDesktop.SetWallpaperOptions(ref wallpaperOpt, 0);			
				activeDesktop.ApplyChanges(AD_Apply.ALL | AD_Apply.FORCE | AD_Apply.BUFFERED_REFRESH);
			} else {
				Console.WriteLine("Invalid Command Line Arguments");
			}
		}
	}
}