using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace notepad
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void txtWord_TextChanged(object sender, EventArgs e)
        {
            if(txtWord.Text == "")
            {
                btnOk.Enabled = false; // 버튼 비활성화
            }
            else
            {
                btnOk.Enabled = true; // 버튼 활성화
            }
        }
    }
}
