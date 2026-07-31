document.addEventListener('DOMContentLoaded', function () {
    // Formato dinámico para la fecha actual en la cabecera
    const dateBadge = document.getElementById('current-date-badge');
    if (dateBadge) {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        const today = new Date();
        dateBadge.textContent = today.toLocaleDateString('es-ES', options);
    }

    // Lógica del Módulo de Calendario
    const calendarDays = document.querySelectorAll('.calendar-day[data-date]');
    const selectedDateLabel = document.getElementById('selected-calendar-date');
    const matchesList = document.getElementById('calendar-matches-list');
    const emptyState = document.getElementById('calendar-empty-state');

    calendarDays.forEach(day => {
        day.addEventListener('click', function () {
            calendarDays.forEach(d => d.classList.remove('selected'));
            this.classList.add('selected');

            const displayDate = this.getAttribute('data-display') || (this.innerText + ' de Julio');
            if (selectedDateLabel) selectedDateLabel.textContent = displayDate;

            const selectedDate = this.getAttribute('data-date');
            if (matchesList && emptyState) {
                const matchCards = matchesList.querySelectorAll('.calendar-match-item[data-match-date]');
                let visibleCount = 0;

                matchCards.forEach(card => {
                    const matchDate = card.getAttribute('data-match-date');
                    if (matchDate === selectedDate) {
                        card.classList.remove('d-none');
                        visibleCount++;
                    } else {
                        card.classList.add('d-none');
                    }
                });

                if (visibleCount === 0 && matchCards.length > 0) {
                    emptyState.classList.remove('d-none');
                } else if (matchCards.length > 0) {
                    emptyState.classList.add('d-none');
                }
            }
        });
    });
});
