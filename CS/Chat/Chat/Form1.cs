using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;



namespace Chat
{
    public partial class Form1 : Form
    {
        // 멤버 변수
        private TcpListener Server;
        private TcpClient Client, ServerClient;
        private NetworkStream myStream;
        private StreamReader myRead;
        private StreamWriter myWrite;
        private Boolean Start = false;
        private Boolean ClientCon = false;

        private int myPort;
        private string myName;
        private Thread myReader, myServer;
        private Boolean TextChange = false;

        private delegate void AddTextDelegate(String strText);

        private AddTextDelegate AddText = null;

        // 애트리뷰트
        [DllImport("User32.dll")]
        private static extern bool FlashWindow(IntPtr hwnd, bool bInvert);

        // 레지스트리 등록
        private RegistryKey key = Registry.LocalMachine.OpenSubKey("SOFTWARE\\Microsoft\\.NETFramework", true);

        private void 설정ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            설정ToolStripMenuItem.Enabled = false; //툴팁 메뉴 사용 막기
            plOption.Visible = true;                //설정 패널 보이기
            txtID.Focus();

            txtID.Text = (String)key.GetValue("Message_name");
            txtPort.Text = (String)key.GetValue("Message_port");
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (cbServer.Checked)   //서버 모드 실행
            {
                ControlCheck();
            }
            else                    //클라이언트 모드 실행
            {
                if (txtIp.Text == "")
                {
                    txtIp.Focus();
                }
                else
                {
                    ControlCheck();
                }
            }
        }

        private void tsddbtnConn_Click(object sender, EventArgs e)
        {
            AddText = new AddTextDelegate(MessageView);

            if (cbServer.Checked == true)
            {
                var addr = new IPAddress(0);

                try
                {
                    myName = (String)key.GetValue("Message_name");
                    myPort = Convert.ToInt32(key.GetValue("Message_port"));
                }
                catch
                {
                    myName = txtID.Text;
                    myPort = Convert.ToInt32(txtPort.Text);
                }

                if (!Start)
                {
                    try
                    {
                        Server = new TcpListener(addr, myPort);

                        Server.Start();

                        Start = true;
                        txtMessage.Enabled = true;
                        btnSend.Enabled = true;
                        txtMessage.Focus();
                        tsddbtnDisconn.Enabled = true;
                        tsddbtnConn.Enabled = false;
                        cbServer.Enabled = false;

                        myServer = new Thread(ServerStart);
                        myServer.Start();

                        설정ToolStripMenuItem.Enabled = false;
                    }
                    catch
                    {
                        Invoke(AddText, "서버를 실행할 수 없습니다.");
                    }
                }
                else
                {
                    ServerStop();
                }
            }
            else
            {
                if (!ClientCon)
                {
                    myName = (String)key.GetValue("Message_name");
                    myPort = Convert.ToInt32(key.GetValue("Message_port"));
                    clientConnection();
                }
                else
                {
                    txtMessage.Enabled = true;
                    btnSend.Enabled = false;
                    DisConnect();
                }
            }
        }

        private void DisConnect()
        {
            ClientCon = false;
            txtMessage.Enabled = false;
            txtMessage.Clear();

            btnSend.Enabled = false;
            tsddbtnConn.Enabled = true;
            tsddbtnDisconn.Enabled = false;

            if (myRead != null)
            {
                myRead.Close();
            }
            if (myWrite != null)
            {
                myWrite.Close();
            }
            if (myStream != null)
            {
                myStream.Close();
            }
            if (ServerClient != null)
            {
                ServerClient.Close();
            }
            if (Server != null)
            {
                Server.Stop();
            }
            if (myReader != null)
            {
                myReader.Abort();
            }
        }

        private void clientConnection()
        {
            try
            {
                Client = new TcpClient(txtIp.Text, myPort);
                Invoke(AddText, "서버에 접속 했습니다.");

                myStream = Client.GetStream();

                myRead = new StreamReader(myStream);
                myWrite = new StreamWriter(myStream);

                ClientCon = true;
                tsddbtnConn.Enabled = false;
                tsddbtnDisconn.Enabled = true;
                txtMessage.Enabled = true;
                btnSend.Enabled = true;
                txtMessage.Focus();

                myReader = new Thread(Receive);
                myReader.Start();
            }
            catch
            {
                ClientCon = false;
                Invoke(AddText, "서버에 접속하지 못했습니다.");
            }
        }

        private void ServerStop()
        {
            Start = false;
            txtMessage.Enabled = false;
            txtMessage.Clear();

            btnSend.Enabled = false;
            tsddbtnConn.Enabled = true;
            tsddbtnDisconn.Enabled = false;

            cbServer.Enabled = true;
            ClientCon = false;

            if(myRead != null)
            {
                myRead.Close();
            }
            if(myWrite != null)
            {
                myWrite.Close();
            }
            if(myStream != null)
            {
                myStream.Close();
            }
            if (ServerClient != null)
            {
                ServerClient.Close();
            }
            if(Server != null)
            {
                Server.Stop();
            }
            if(myReader != null)
            {
                myReader.Abort();
            }

        }

        private void ServerStart()
        {
            Invoke(AddText, "서버 실행 : 챗 상대의 접속을 기다립니다...");

            while (Start)
            {
                try
                {
                    ServerClient = Server.AcceptTcpClient();
                    Invoke(AddText, "챗 상대 접속..");
                    myStream = ServerClient.GetStream();

                    myRead = new StreamReader(myStream);
                    myWrite = new StreamWriter(myStream);
                    ClientCon = true;

                    myReader = new Thread(Receive);
                    myReader.Start();
                }
                catch { }
            }
        }

        private void Receive()
        {
            try
            {
                while (ClientCon)
                {
                    if (myStream.CanRead)
                    {
                        var msg = myRead.ReadLine();
                        var Smsg = msg.Split('&');
                        if (Smsg[0] == "S001")
                        {
                            tsslblTime.Text = Smsg[1];
                        }
                        else
                        {
                            if (msg.Length > 0)
                            {
                                // TODO: invoke 공부
                                Invoke(AddText, Smsg[0] + " : " + Smsg[1]);
                            }
                            tsslblTime.Text = "마지막으로 받은 시각 : " + Smsg[2];
                        }
                    }
                }
            }
            catch { }
        }

        private void MessageView(string strText)
        {
            rtbText.AppendText(strText + "\n");
            rtbText.Focus();
            rtbText.ScrollToCaret();
            txtMessage.Focus();
            FlashWindow(this.Handle, true);
        }

        private void tsddbtnDisconn_Click(object sender, EventArgs e)
        {
            try
            {
                if (cbServer.Checked)
                {
                    if (ServerClient.Connected)
                    {
                        var dt = Convert.ToString(DateTime.Now);
                        myWrite.WriteLine(myName + "&" + "채팅 App가 종료되었습니다." + "&" + dt);
                        myWrite.Flush();
                    }
                }
                else
                {
                    if (Client.Connected)
                    {
                        var dt = Convert.ToString(DateTime.Now);
                        myWrite.WriteLine(myName + "&" + "채팅 App가 종료되었습니다." + "&" + dt);
                        myWrite.Flush();
                    }
                }
            }
            catch
            {

            }
            ServerStop();
            설정ToolStripMenuItem.Enabled = true;
        }

        private void btnSend_Click(object sender, EventArgs e)
        {
            if (txtMessage.Text == "")
            {
                txtMessage.Focus();
            }
            else
            {
                Mes_send();
            }
        }

        private void Mes_send()
        {
            try
            {
                var dt = Convert.ToString(DateTime.Now);

                myWrite.WriteLine(myName + "&" + txtMessage.Text + "&" + dt);
                myWrite.Flush();

                MessageView(myName + ":" + txtMessage.Text);
                txtMessage.Clear();
            }
            catch
            {
                Invoke(AddText, "데이터를 보내는 동안 오류가 발생했습니다.");
                txtMessage.Clear();
            }
        }

        private void txtMessage_TextChanged(object sender, EventArgs e)
        {
            if (TextChange == false)
            {
                TextChange = true;
                myWrite.WriteLine("S001" + "&" + "상대방이 메시지를 입력중입니다." + "&" + "");
                myWrite.Flush();
            }
            else if (txtMessage.Text == "" && TextChange == true)
            {
                TextChange = false;
            }
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            try
            {
                ServerStop();
            }
            catch
            {
                DisConnect();
            }
        }

        private void ControlCheck()
        {
            if (txtID.Text == "")
            {
                txtID.Focus();
            }
            else if (txtPort.Text == "")
            {
                txtPort.Focus();
            }
            else
            {
                try
                {
                    var name = txtID.Text;
                    var port = txtPort.Text;

                    key.SetValue("Message_name", name);
                    key.SetValue("Message_port", port);


                    plOption.Visible = false;
                    설정ToolStripMenuItem.Enabled = true;
                    tsddbtnConn.Enabled = true;
                }
                catch
                {
                    MessageBox.Show("설정이 저장되지 않았습니다.", "에러",
                        MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }

        public Form1()
        {
            InitializeComponent();
        }
    }
}
