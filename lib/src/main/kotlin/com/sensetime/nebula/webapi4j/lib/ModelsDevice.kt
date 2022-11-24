package com.sensetime.nebula.webapi4j.lib


data class Role(
    val roleId: Long,
    val roleName: String
)

data class Customer(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phones: List<String>,
    val email: String,
    val address: String
)

data class Supplier(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phones: List<String>,
    val email: String,
    val address: String
)

data class DeviceModelVersion(
    val hunter: String,
    val august: String,
    val aligner: String,
    val headpose: String,
    val blur: String,
    val liveness: String,
    val feature: String,
    val attribute: String
)

data class DeviceInfo(
    val model: String,
    val device_sn: String,
    val mac_wired: String,
    val mac_wlan: String,
    val sw_ver: String,
    val hw_ver: String,
    val app_ver: String,
    val api_ver: String,
    val model_ver: DeviceModelVersion
)

data class DeviceRecognize(
    val use_mode: Int,
    val multi_auth_mode: Boolean,
    val multi_auth_timeout: Int,
    val liveness: Boolean,
    val liveness_threshold: Double,
    val verify_threshold: Double,
    val certificate_threshold: Double,
    val recognition_distance: Double,
    val open_interval: Int,
    val mask_detect: Boolean,
    val no_access_without_mask: Boolean,
    val no_access_with_mask: Boolean
)

data class DeviceAccess(
    val open_door_type: Int,
    val keep_door_open_duration: Int,
    val gpio_a: Int,
    val gpio_b: Int,
    val gpio_c: Int,
    val wigan_input: Int,
    val tamper: Boolean,
    val force_open: Boolean,
    val network_relay_address: String,
    val door_sensor_timeout: Int
)

data class DeviceFunctions(
    val device_run_type: Int,
    val mode: Int,
    val strong_hint: Boolean,
    val avatar_status: Int,
    val name_status: Int,
    val record: Boolean,
    val record_image: Boolean,
    val alarm_record: Boolean,
    val auth_mode: Int,
    val remote_authentication_address: String
)

data class DeviceSystem(
    val language_type: Int,
    val sound_volume: Int,
    val screen_brightness: Int,
    val auto_reboot: Boolean,
    val reboot_time: String,
    val standby_open: Boolean,
    val standby_touch_wakeup: Boolean,
    val wait_time: Int,
    val sleep_time: Int,
    val touch_recognition: Boolean,
    val touch_recognition_return_time: Int,
    val touch_recognition_timeout: Int,
    val short_exposure: Int
)

data class DeviceCustom(
    val welcome_tip: String,
    val verify_success_tip: String,
    val verify_fault_tip: String,
    val unauthorized_user_tip: String,
    val show_custom_logo: Boolean,
    val custom_picture_for_logo: String,
    val custom_picture_for_idle: String,
    val voice_broadcast: Boolean
)

data class DeviceTime(
    val local_time: Int,
    val manual_mode: Boolean = false,
    val time_zone: Int = 8,
    val server_address: String = "https://link.bi.sensetime.com/sl"
)

data class DeviceDoor(
    val open_mode: Int,
    val card_number: String
)