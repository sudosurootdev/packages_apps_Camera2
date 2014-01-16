/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.camera.app;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.android.camera.app.LegacyLocationProvider;

/**
 * A class to select the best available location provider (fused location
 * provider, or network/gps if the fused location provider is unavailable)
 * and provide a common location interface.
 */
public class LocationManager {
    private final String TAG = "LocationManager";
    LocationProvider mLocationProvider;
    private boolean mRecordLocation;

    public LocationManager(Activity activity) {
        Log.d(TAG, "Using legacy location provider.");
        LegacyLocationProvider llp = new LegacyLocationProvider(activity);
        mLocationProvider = llp;
    }

    /**
     * Start/stop location recording.
     */
    public void recordLocation(boolean recordLocation) {
        mRecordLocation = recordLocation;
        mLocationProvider.recordLocation(mRecordLocation);
    }

    /**
     * Returns the current location from the location provider.
     */
    public Location getCurrentLocation() {
        return mLocationProvider.getCurrentLocation();
    }

    /*
     * Disconnects the location provider.
     */
    public void disconnect() {
        mLocationProvider.disconnect();
    }
}