using Client_App_IS.Common;
using Client_App_IS.FrontEndWebService;
using Client_App_IS.Model;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Popups;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// The Basic Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=234237

namespace Client_App_IS
{
    /// <summary>
    /// A basic page that provides characteristics common to most applications.
    /// </summary>
    public sealed partial class AddDevicePage : Page
    {

        private NavigationHelper navigationHelper;
        private ObservableDictionary defaultViewModel = new ObservableDictionary();
        private UserModel currentUser;

        /// <summary>
        /// This can be changed to a strongly typed view model.
        /// </summary>
        public ObservableDictionary DefaultViewModel
        {
            get { return this.defaultViewModel; }
        }

        /// <summary>
        /// NavigationHelper is used on each page to aid in navigation and 
        /// process lifetime management
        /// </summary>
        public NavigationHelper NavigationHelper
        {
            get { return this.navigationHelper; }
        }


        public AddDevicePage()
        {
            this.InitializeComponent();
            this.navigationHelper = new NavigationHelper(this);
            this.navigationHelper.LoadState += navigationHelper_LoadState;
            this.navigationHelper.SaveState += navigationHelper_SaveState;
        }

        /// <summary>
        /// Populates the page with content passed during navigation. Any saved state is also
        /// provided when recreating a page from a prior session.
        /// </summary>
        /// <param name="sender">
        /// The source of the event; typically <see cref="NavigationHelper"/>
        /// </param>
        /// <param name="e">Event data that provides both the navigation parameter passed to
        /// <see cref="Frame.Navigate(Type, Object)"/> when this page was initially requested and
        /// a dictionary of state preserved by this page during an earlier
        /// session. The state will be null the first time a page is visited.</param>
        private void navigationHelper_LoadState(object sender, LoadStateEventArgs e)
        {
            currentUser = e.NavigationParameter as UserModel;
            //defaultViewModel.Add("Types", currentUser.deviceTypes);
            ObservableCollection<DeviceType> teste = new ObservableCollection<DeviceType>();
            foreach (KeyValuePair<String, object> item in currentUser.deviceTypes)
            {
                DeviceType type = new DeviceType(Convert.ToInt32(item.Key), item.Value as String);
                teste.Add(type);
            }
            TypeBox.DataContext = teste;
        }

        /// <summary>
        /// Preserves state associated with this page in case the application is suspended or the
        /// page is discarded from the navigation cache.  Values must conform to the serialization
        /// requirements of <see cref="SuspensionManager.SessionState"/>.
        /// </summary>
        /// <param name="sender">The source of the event; typically <see cref="NavigationHelper"/></param>
        /// <param name="e">Event data that provides an empty dictionary to be populated with
        /// serializable state.</param>
        private void navigationHelper_SaveState(object sender, SaveStateEventArgs e)
        {
           
        }

        #region NavigationHelper registration

        /// The methods provided in this section are simply used to allow
        /// NavigationHelper to respond to the page's navigation methods.
        /// 
        /// Page specific logic should be placed in event handlers for the  
        /// <see cref="GridCS.Common.NavigationHelper.LoadState"/>
        /// and <see cref="GridCS.Common.NavigationHelper.SaveState"/>.
        /// The navigation parameter is available in the LoadState method 
        /// in addition to page state preserved during an earlier session.

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            navigationHelper.OnNavigatedTo(e);
        }

        protected override void OnNavigatedFrom(NavigationEventArgs e)
        {
            navigationHelper.OnNavigatedFrom(e);
        }

        #endregion

        private async void RegisterButton_Click(object sender, RoutedEventArgs e)
        {
            FrontEndWebServiceClient client = new FrontEndWebServiceClient();
            MessageDialog msg;
            int serialNumber = 0;
            bool exception=false;
            DeviceType selectedType = TypeBox.SelectedItem as DeviceType;
            await client.OpenAsync();
            try
            {
                serialNumber = Convert.ToInt32(serialBox.Text);
            }
            catch (Exception)
            {
                exception = true;
            }
            if (exception)
            {
                msg = new MessageDialog("The serial number must be a number!");
                await msg.ShowAsync();
                return;
            }
            
            addDeviceResponse res = await client.addDeviceAsync(serialNumber, currentUser.UserName, ModelBox.Text, nameBox.Text,selectedType.Key);
            if (res.@return)
            {
                msg = new MessageDialog("Device created successfully!");
                currentUser.Devices.Add(new DeviceModel(currentUser.UserName,nameBox.Text,serialNumber,selectedType.Value));
                await msg.ShowAsync();
                this.Frame.Navigate(typeof(UserPage),currentUser);
            }
            else
            {
                msg = new MessageDialog("Error creating the device, please try again!");
                await msg.ShowAsync();
            }
        }
    }
}