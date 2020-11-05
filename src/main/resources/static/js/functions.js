function formatUTC(utc) {
    let dateTime = new Date(utc);
    return dateTime.toLocaleDateString() + " " + dateTime.toLocaleTimeString();
}