using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Client_App_IS.Model
{
    public class DeviceData
    {
        public DateTime Time { get; set; }
        public String StateDescription { get; set; }
        public String ErrorDescription { get; set; }
        public int EnerProd { get; set; }
    }
}
