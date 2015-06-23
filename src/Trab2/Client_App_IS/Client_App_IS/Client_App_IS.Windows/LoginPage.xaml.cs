using Client_App_IS.Common;
using Client_App_IS.FrontEndWebService;
using Client_App_IS.Model;
using System;
using System.Collections.Generic;
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
    public sealed partial class LoginPage : Page
    {

        private NavigationHelper navigationHelper;
        private ObservableDictionary defaultViewModel = new ObservableDictionary();

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


        public LoginPage()
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

        private async void Button_Click(object sender, RoutedEventArgs e)
        {
            FrontEndWebServiceClient client = new FrontEndWebServiceClient();
            MessageDialog msg;
            loginUserResponse res2;
            try
            {
                await client.OpenAsync();
                //await client.createDBAsync();
                ring.IsActive = true;
                //addUserResponse res1 = await client.addUserAsync("jmdbo", "João Barata", 217793070, "Mem Martins", "Hash");
                //addDeviceResponse res = await client.addDeviceAsync(1, "jmdbo", "SocketBee", "Hello", 1);
                res2 = await client.loginUserAsync(userBox.Text, passBox.Password);
                msg = new MessageDialog("Resultado " + res2.@return.ToString());
                await msg.ShowAsync();
                if (res2.@return)
                {
                    UserModel user = new UserModel(userBox.Text);
                    ObservableDictionary deviceTypes = new ObservableDictionary();
                    getDeviceTypesResponse devTyp = await client.getDeviceTypesAsync();
                    String[] devTypResponse = devTyp.@return;
                    if(devTypResponse!=null){
                        for (int i = 0; i < (devTypResponse.Length/2); i++)
			            {
                            deviceTypes.Add(devTypResponse[2*i],devTypResponse[(2*i)+1]);                                
			            }
                    }
                    else{
                        return;
                    }
                    getUserDevicesResponse devReq = await client.getUserDevicesAsync(user.UserName);
                    String[] userDevices = devReq.@return;
                    if (userDevices != null) {
                        for (int i = 0; i < (userDevices.Length/3); i++)
                        {
                            object type;
                            deviceTypes.TryGetValue(userDevices[(3 * i) + 2],out type);
                            DeviceModel device = new DeviceModel(user.UserName, userDevices[(3 * i) + 1], Convert.ToInt32(userDevices[3 * i]),(String)type);
                            user.Devices.Add(device);
                        
                        }
                    }
                    user.deviceTypes = deviceTypes;
                    this.Frame.Navigate(typeof(UserPage),user);
                    return;
                }
                else
                {
                    ring.IsActive = false;
                    return;
                }

            }
            catch (Exception ex)
            {
                msg = new MessageDialog("Erro de comunicação! " + ex.Message);
                ring.IsActive = false;
            }
            await msg.ShowAsync();
            
            
        }

        private void Register_Click(object sender, RoutedEventArgs e)
        {
            this.Frame.Navigate(typeof(RegisterPage));
        }
    }
}
