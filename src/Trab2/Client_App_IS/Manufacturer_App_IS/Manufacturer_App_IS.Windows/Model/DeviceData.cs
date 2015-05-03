using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Manufacturer_App_IS.Model
{
    public class DeviceData
    {
        public DateTime Time { get; set; }
        public String StateDescription { get; set; }
        public String ErrorDescription { get; set; }
        public String EnerProd { get; set; }
        public String SerialNumber { get; set; }
        public String DeviceType { get; set; }

        public DeviceData(String date, String state, String error, String ener, String serial, String type)
        {
            this.Time = Convert.ToDateTime(date);
            this.StateDescription = state;
            this.ErrorDescription = error;
            this.EnerProd = ener;
            this.SerialNumber = serial;
            this.DeviceType = type;
        }

        public DeviceData(String date, String state, String error, String ener)
        {
            this.Time = Convert.ToDateTime(date);
            this.StateDescription = state;
            this.ErrorDescription = error;
            this.EnerProd = ener;            
        }
    }
}
