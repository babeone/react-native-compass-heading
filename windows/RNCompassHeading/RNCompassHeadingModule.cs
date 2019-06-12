using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Compass.Heading.RNCompassHeading
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNCompassHeadingModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNCompassHeadingModule"/>.
        /// </summary>
        internal RNCompassHeadingModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNCompassHeading";
            }
        }
    }
}
