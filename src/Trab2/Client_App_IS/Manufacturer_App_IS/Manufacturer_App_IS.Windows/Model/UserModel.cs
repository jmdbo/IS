using Manufacturer_App_IS.Common;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Manufacturer_App_IS.Model
{
    class UserModel
    {
        public ObservableDictionary deviceTypes = new ObservableDictionary();

        public String UserName { get; set; }

        public int UserType { get; set; }

        public String Name { get; set; }

        public String Token { get; set; }

        public UserModel(String user, int type)
        {
            UserName = user;
            UserType = type;
        }

    }
}
