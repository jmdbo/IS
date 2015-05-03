using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Client_App_IS.Model
{
    class DeviceModel
    {
        public ObservableCollection<DeviceData> deviceData { get; set; }

        private String _name;

        public String Name
        {
            get { return _name; }
            set { _name = value; }
        }
        private String _model;

        public String Model
        {
            get { return _model; }
            set { _model = value; }
        }

        private int _serialNumber;

        public int SerialNumber
        {
            get { return _serialNumber; }
            set { _serialNumber = value; }
        }

        private String _type;

        public String Type
        {
            get { return _type; }
            set { _type = value; }
        }

        private String _user;

	    public String User
	    {
		    get { return _user;}
		    set { _user = value;}
	    }


        public DeviceModel(String name, String model, String user, int serial, String type)
        {
            deviceData = new ObservableCollection<DeviceData>();
            _name = name;
            _model = model;
            _user = user;
            _serialNumber = serial;
            _type = type;
        }
        public DeviceModel(String user, String name, int serial, String type)
        {
            deviceData = new ObservableCollection<DeviceData>();
            _name = name;
            _model = "";
            _user = user;
            _serialNumber = serial;
            _type = type;
        }
    }
}
