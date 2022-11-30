using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace notepad
{
    public partial class Form1 : Form
    {
        private Boolean txtNoteChange; // 내용 변경 확인
        private String fWord; // 찾기 문자열
        private Form2 frm2; // 찾기 폼
        private Form3 fr3;
        public Form1()
        {
            InitializeComponent();
        }

        private void 열기OToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (txtNoteChange)
            {
                var msg = Text + " 파일 내용이 변경되었습니다.\r\n변경된 내용을 저장하시겠습니까?";
                DialogResult dir = MessageBox.Show(msg, "메모장", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Question);

                if (dir == DialogResult.Yes)
                {
                    txtSave();
                    txtOpen();
                }
            }
            else
            {
                txtOpen();
            }
        }

        private void txtOpen()
        {
            DialogResult dir = openFileDialog1.ShowDialog();
            if (dir == DialogResult.OK)
            {
                String str = openFileDialog1.FileName;
                StreamReader sr = new StreamReader(str, System.Text.Encoding.Default);
                txtNote.Text = sr.ReadToEnd();
                sr.Close();

                FileInfo f = new FileInfo(str);
                Text = f.Name;
                txtNoteChange = false;
            }
        }

        private void 저장SToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txtSave();
        }

        private void txtSave()
        {
            if (Text == "메모장")
            {
                DialogResult dialogResult = saveFileDialog1.ShowDialog();
                if (dialogResult != DialogResult.Cancel)
                {
                    String str = saveFileDialog1.FileName;
                    StreamWriter sw = new StreamWriter(str, false, System.Text.Encoding.Default);
                    sw.Write(txtNote.Text);
                    sw.Flush();
                    sw.Close();

                    FileInfo fileInfo = new FileInfo(str);
                    Text = fileInfo.Name;
                }
            }
            else
            {
                String str = Text;
                StreamWriter sw = new StreamWriter(str, false, System.Text.Encoding.Default);
                sw.Write(txtNote.Text);
                sw.Flush();
                sw.Close();
            }
        }

        private void txtNote_TextChanged(object sender, EventArgs e)
        {
            txtNoteChange = true;
        }

        private void 새로만들기NToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (txtNoteChange)
            {
                var msg = Text + " 파일 내용이 변경되었습니다.\r\n변경된 내용을 저장하시겠습니까?";
                DialogResult dir = MessageBox.Show(msg, "메모장", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Question);

                if (dir == DialogResult.Yes)
                {
                    txtSave();
                    txtNote.ResetText();
                    Text = "메모장";
                }
                else if (dir == DialogResult.No)
                {
                    txtNote.ResetText();
                    Text = "메모장";
                    txtNoteChange = false;
                }
                else
                {
                    return;
                }
            }
            else
            {
                txtNote.ResetText();
                Text = "메모장";
                txtNoteChange = false;
            }
        }

        private void 다른이름으로저장AToolStripMenuItem_Click(object sender, EventArgs e)
        {
            DialogResult dir = saveFileDialog1.ShowDialog();
            if (dir == DialogResult.Yes)
            {
                String str = saveFileDialog1.FileName;
                StreamWriter sw = new StreamWriter(str, false, System.Text.Encoding.Default);
                sw.Write(txtNote.Text);
                sw.Flush();
                sw.Close();

                FileInfo fileInfo = new FileInfo(str);
                Text = fileInfo.Name;
                txtNoteChange = false;
            }
        }

        private void 끝내기QToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (txtNoteChange)
            {
                var msg = Text + " 파일 내용이 변경되었습니다.\r\n변경된 내용을 저장하시겠습니까?";
                DialogResult dir = MessageBox.Show(msg, "메모장", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Question);
                if (dir == DialogResult.Yes)
                {
                    txtSave();
                    Close();
                }
                else
                {
                    return;
                }
            }
            Close();
        }

        private void 자동줄바꿈WToolStripMenuItem_Click(object sender, EventArgs e)
        {
            자동줄바꿈WToolStripMenuItem.Checked = !(자동줄바꿈WToolStripMenuItem.Checked);
            txtNote.WordWrap = !(txtNote.WordWrap);
        }

        private void 글꼴ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            DialogResult dir = fontDialog1.ShowDialog();
            if (dir == DialogResult.OK)
            {
                txtNote.Font = fontDialog1.Font;
            }
        }

        private void 실행취소UToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txtNote.Undo();
        }

        private void 잘라내기TToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txtNote.Cut();
        }

        private void 복사PToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txtNote.Copy();
        }

        private void 붙여넣기PToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txtNote.Paste();
        }

        private void 삭제LToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txtNote.SelectedText = "";
        }

        private void 모두선택AToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txtNote.SelectAll();
        }

        private void 시간날짜DToolStripMenuItem_Click(object sender, EventArgs e)
        {
            String time = DateTime.Now.ToShortDateString();
            String day = DateTime.Now.ToShortDateString();
            txtNote.AppendText(time + " : " + day);
        }

        private void 메모장정보AToolStripMenuItem_Click(object sender, EventArgs e)
        {
            fr3 = new Form3();
            fr3.ShowDialog();
        }

        private void 찾기FToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (!(frm2 == null || !frm2.Visible))
            {
                frm2.Focus();
                return;
            }
            frm2 = new Form2();
            if (txtNote.SelectionLength != 0)
            {
                frm2.txtWord.Text = txtNote.SelectedText;
            }
            else
            {
                MessageBox.Show("텍스트를 선택하세요");
                return;
            }
            frm2.btnOk.Click += new System.EventHandler(btnOk_Click);
            frm2.Show();
        }

        private void btnOk_Click(object sender, EventArgs e)
        {
            var updown = -1;
            var str = txtNote.Text;
            var findWord = frm2.txtWord.Text;

            if (!frm2.chkCase.Checked)
            {
                str = str.ToUpper();
                findWord = findWord.ToUpper();
            }

            if (frm2.rdBtnUp.Checked)
            {
                if (txtNote.SelectionStart != 0)
                {
                    updown = str.LastIndexOf(findWord, txtNote.SelectionStart - 1);
                }
            }
            else
            {
                updown = str.IndexOf(findWord, txtNote.SelectionStart + txtNote.SelectionLength);
            }

            if (updown == -1)
            {
                MessageBox.Show("더 이상 찾는 문자열이 없습니다.", "찾기", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            txtNote.Select(updown, findWord.Length);
            fWord = frm2.txtWord.Text;
            txtNote.Focus();
            txtNote.ScrollToCaret();
        }
    }
}
