using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using System.Drawing.Imaging;

namespace ScreenCapture
{
    public partial class Form1 : Form
    {
        Point oriLocalPoint;
        Size orgLocalSize;

        bool orgbool = true;
        bool capbool = false;

        Graphics ScreenG;
        Bitmap CapWin;

        System.Media.SoundPlayer soundPlayer = new System.Media.SoundPlayer();
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar == 'c')
            {
                orgbool = true;
                capbool = true;

                this.Opacity = 0.0;
                this.FormBorderStyle = FormBorderStyle.None;
                this.Location = new Point(0, 0);
                this.Size = Screen.PrimaryScreen.Bounds.Size;
                var fullScreen = Screen.PrimaryScreen.Bounds;

                CapWin = new Bitmap(fullScreen.Width, fullScreen.Height);

                ScreenG = Graphics.FromImage(CapWin);

                ScreenG.CopyFromScreen(PointToScreen(new Point(0, 0)), new Point(0, 0), fullScreen.Size);

                picbScreen.Image = CapWin;

                soundPlayer.SoundLocation = @"..\..\wav\capture.wav";
                soundPlayer.Play();

                this.Opacity = 100.0;
                this.FormBorderStyle = FormBorderStyle.FixedSingle;
                this.Location = oriLocalPoint;
            }
            else if (e.KeyChar == 'e')
            {
                soundPlayer.SoundLocation = @"..\..\wav\ereser.wav";
                soundPlayer.Play();

                capbool = false;
                orgbool = false;
                picbScreen.Image = null;
            }
            else if (e.KeyChar == 's')
            {
                if (capbool)
                {
                    using (var SFile = new SaveFileDialog())
                    {
                        SFile.OverwritePrompt = true;
                        SFile.FileName = "화면캡쳐";
                        SFile.Filter = "이미지 파일(*.jpg)|*.jpg";
                        DialogResult result = SFile.ShowDialog();
                        if (result == DialogResult.OK)
                        {
                            CapWin.Save(SFile.FileName, ImageFormat.Jpeg);
                        }
                    }
                }
                else
                {
                    MessageBox.Show("캡쳐한 화면이 없습니다.", "알림", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }
        }

        private void Form1_LocationChanged(object sender, EventArgs e)
        {
            if (orgbool)
            {
                oriLocalPoint = Location;
                orgLocalSize = Size;
            }
        }
    }
}
