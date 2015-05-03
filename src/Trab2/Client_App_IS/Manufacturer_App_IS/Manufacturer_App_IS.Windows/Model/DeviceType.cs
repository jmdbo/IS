using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Manufacturer_App_IS.Model
{
    class DeviceType
    {
        public int Key;

        public String Value;

        public DeviceType(int k, String val)
        {
            Key = k;
            Value = val;
        }

        public override string ToString()
        {
            return this.Value;
        }
    }
}
