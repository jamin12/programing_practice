using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data.MySqlClient;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace AddressBook
{
    public partial class Form1 : Form
    {
        string connStr = "Server=localhost;Database=address;Uid=root;Pwd=123456798";
        string selectedItemId = "";

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            lvList_DB_View();
            txtName.Focus();
        }

        private void lvList_DB_View()
        {
            lvList.Items.Clear();

            using (MySqlConnection conn = new MySqlConnection(connStr))
            {
                conn.Open();
                String sql = "SELECT id, name, age, phone, job FROM t_info";

                MySqlCommand cmd = new MySqlCommand(sql, conn);
                MySqlDataReader dr = cmd.ExecuteReader();
                while(dr.Read())
                {
                    lvList.Items.Add(new ListViewItem(new String[]
                    {
                        dr[0].ToString(),
                        dr[1].ToString(),
                        dr[2].ToString(),
                        dr[3].ToString(),
                        dr[4].ToString()
                    }));
                }
                dr.Close();
                conn.Close();
            }
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            txtSave();
        }
        private void txtSave()
        {
            if (txtCheck())
            {
                using (MySqlConnection conn = new MySqlConnection(connStr))
                {
                    conn.Open();

                    string sql = "INSERT INTO t_info (name, phone, job, age) " +
                        "VALUES('" + txtName.Text + "', '" + txtTel.Text + "', '" + txtJob.Text + "', " + int.Parse(txtAge.Text) + ")";
                    Console.WriteLine(sql);

                    MySqlCommand cmd = new MySqlCommand(sql, conn);
                    int i = cmd.ExecuteNonQuery();
                    if (i == 1)
                    {
                        MessageBox.Show("정상적으로 데이터를 저장되었습니다.", "알림", MessageBoxButtons.OK, MessageBoxIcon.Information);

                        lvList_DB_View();
                        txtClear();
                        txtName.Focus();
                    }
                }
            }
        }

        private void txtClear()
        {
            txtName.Text = "";
            txtTel.Text = "";
            txtAge.Text = "";
            txtJob.Text = "";
        }

        private bool txtCheck()
        {
            if(txtName.Text == "")
            {
                txtName.Focus();
                return false;
            }
            if(txtAge.Text == "")
            {
                txtName.Focus();
                return false;
            }
            if(txtTel.Text == "")
            {
                txtName.Focus();
                return false;
            }
            if(txtJob.Text == "")
            {
                txtName.Focus();
                return false;
            }
            return true;
        }

        private void btnModify_Click(object sender, EventArgs e)
        {
            if (lvList.SelectedIndices.Count != 0)
            {
                if (txtCheck())
                {
                    using (MySqlConnection conn = new MySqlConnection(connStr))
                    {
                        conn.Open();

                        string sql = "UPDATE t_info " +
                            "SET name='" + txtName.Text + "', phone='" + txtTel.Text + "', job='" + txtJob.Text + "', age=" + int.Parse(txtAge.Text) + " " +
                            "WHERE id=" + lvList.SelectedItems[0].SubItems[0].Text;
                        Console.WriteLine(sql);

                        MySqlCommand cmd = new MySqlCommand(sql, conn);
                        int i = cmd.ExecuteNonQuery();
                        if (i == 1)
                        {
                            MessageBox.Show("정상적으로 데이터가 수정되었습니다.", "알림", MessageBoxButtons.OK, MessageBoxIcon.Information);

                            lvList_DB_View();
                            txtClear();
                            txtName.Focus();
                        }
                    }
                }
            }
            else
            {
                MessageBox.Show("수정할 요소를 선택해주세요.");
                return;
            }
        }

        private void lvList_ItemSelectionChanged(object sender, ListViewItemSelectionChangedEventArgs e)
        {
            if (lvList.SelectedItems.Count > 0)
            {
                ListViewItem lvItem = lvList.SelectedItems[0];

                txtName.Text = lvItem.SubItems[1].Text;
                txtTel.Text = lvItem.SubItems[3].Text;
                txtAge.Text = lvItem.SubItems[2].Text;
                txtJob.Text = lvItem.SubItems[4].Text;
                
                selectedItemId = lvItem.SubItems[0].Text;
            }
        }

        private void btnDel_Click(object sender, EventArgs e)
        {
            if (lvList.SelectedIndices.Count != 0)
            {
                if (txtCheck())
                {
                    using (MySqlConnection conn = new MySqlConnection(connStr))
                    {
                        conn.Open();

                        string sql = "DELETE FROM t_info " +
                            "WHERE id=" + lvList.SelectedItems[0].SubItems[0].Text;
                        Console.WriteLine(sql);

                        MySqlCommand cmd = new MySqlCommand(sql, conn);
                        int i = cmd.ExecuteNonQuery();
                        if (i == 1)
                        {
                            MessageBox.Show("정상적으로 데이터가 삭제되었습니다.", "알림", MessageBoxButtons.OK, MessageBoxIcon.Information);

                            lvList_DB_View();
                            txtClear();
                            txtName.Focus();
                        }
                    }
                }
            }
            else
            {
                MessageBox.Show("삭제할 요소를 선택해주세요.");
                return;
            }
        }

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            if(e.KeyData == Keys.Enter)
            {
                txtSave();
            }
        }
    }
}
