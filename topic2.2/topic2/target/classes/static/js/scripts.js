        document.getElementById('amount').addEventListener('input', function() {
            if (this.validity.rangeOverflow) {
                this.setCustomValidity('Az összeg nem lehet több mint 50000 Ft.');
            } else if (this.validity.rangeUnderflow) {
                this.setCustomValidity('Az összeg nem lehet kevesebb mint 1500 Ft.');
            } else {
                this.setCustomValidity('');
            }
        });

        document.getElementById('weight').addEventListener('input', function() {
            if (this.validity.rangeOverflow) {
                this.setCustomValidity('A súly nem lehet több mint 180 Kg.');
            } else if (this.validity.rangeUnderflow) {
                this.setCustomValidity('A súly nem lehet kevesebb mint 40 Kg.');
            } else {
                this.setCustomValidity('');
            }
        });
