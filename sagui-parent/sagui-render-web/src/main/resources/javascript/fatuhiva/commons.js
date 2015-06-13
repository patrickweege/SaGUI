var Util = Util || {
	comparator : new ComparatorUtil()
}

function ComparatorUtil() {
	this.isNull = function(toCheck) {
		if (typeof (toCheck) !== 'undefined' && toCheck !== null) {
			return false;
		}
		return true;
	};

	this.isString = function(toCheck) {
		return !this.isNull(toCheck) && (typeof toCheck === 'string');
	};

	this.isBetween = function(theStart, theEnd, toCheck) {
		var between = false;
		if (DateUtil.isDate(theStart) && DateUtil.isDate(theEnd) && DateUtil.isDate(toCheck)) {
			between = DateUtil.isBetween(theStart, theEnd, toCheck);
		}
		return between;
	};
};

function DateUtils() {
	this.isDate = function(toCheck) {
		if (toCheck instanceof Date) {
			return true;
		}
		return false;
	};

	this.getDayDiff = function(d1, d2) {
		var t2 = d2.getTime();
		var t1 = d1.getTime();

		return parseInt((t2 - t1) / (24 * 3600 * 1000));
	};

	this.getWeekDiff = function(d1, d2) {
		var t2 = d2.getTime();
		var t1 = d1.getTime();
		return parseInt((t2 - t1) / (24 * 3600 * 1000 * 7));
	};

	this.getMonthDiff = function(d1, d2) {
		var d1Y = d1.getFullYear();
		var d2Y = d2.getFullYear();
		var d1M = d1.getMonth();
		var d2M = d2.getMonth();

		return (d2M + 12 * d2Y) - (d1M + 12 * d1Y);
	};

	this.getYearDiff = function(d1, d2) {
		return d2.getFullYear() - d1.getFullYear();
	};

	this.isBetween = function(fromDate, toDate, checkDate, checkTime) {
		var newFrom = fromDate.getTime() <= toDate.getTime() ? fromDate : toDate;
		var newTo = toDate.getTime() >= fromDate.getTime() ? toDate : fromDate;

		if (!checkTime) {
			newFrom = new Date(newFrom.getYear(), newFrom.getMonth(), newFrom.getDate(), 0, 0, 0, 0);
			newTo = new Date(newTo.getYear(), newTo.getMonth(), newTo.getDate(), 0, 0, 0, 0);
		}
		if (checkDate.getTime() >= newFrom.getTime() && checkDate.getTime() <= newTo.getTime()) {
			return true;
		} else {
			return false;
		}
	};
};
