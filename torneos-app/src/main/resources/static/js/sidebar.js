document.addEventListener('DOMContentLoaded', function () {
    const triggers = document.querySelectorAll('.sidebar-logo-trigger');
    const sidebar = document.getElementById('app-sidebar');
    const backdrop = document.getElementById('sidebar-backdrop');
    const closeBtn = document.getElementById('sidebar-close-btn');

    if (!sidebar || triggers.length === 0) return;

    let isOpen = false;

    function openSidebar() {
        if (isOpen) return;
        isOpen = true;
        sidebar.classList.add('open');
        if (backdrop) backdrop.classList.add('active');
        sidebar.setAttribute('aria-hidden', 'false');
        triggers.forEach(t => t.setAttribute('aria-expanded', 'true'));
        document.body.style.overflow = 'hidden';

        if (closeBtn) {
            setTimeout(() => closeBtn.focus(), 100);
        }
    }

    function closeSidebar() {
        if (!isOpen) return;
        isOpen = false;
        sidebar.classList.remove('open');
        if (backdrop) backdrop.classList.remove('active');
        sidebar.setAttribute('aria-hidden', 'true');
        triggers.forEach(t => t.setAttribute('aria-expanded', 'false'));
        document.body.style.overflow = '';
    }

    function toggleSidebar() {
        if (isOpen) {
            closeSidebar();
        } else {
            openSidebar();
        }
    }

    triggers.forEach(trigger => {
        trigger.addEventListener('click', toggleSidebar);
        trigger.addEventListener('keydown', function (e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                toggleSidebar();
            }
        });
    });

    if (closeBtn) {
        closeBtn.addEventListener('click', closeSidebar);
    }

    if (backdrop) {
        backdrop.addEventListener('click', closeSidebar);
    }

    document.addEventListener('keydown', function (e) {
        if (e.key === 'Escape' && isOpen) {
            closeSidebar();
        }
    });

    // Resaltar elemento de menú activo según la URL actual
    const currentPath = window.location.pathname;
    const links = sidebar.querySelectorAll('.sidebar-link[data-path]');

    links.forEach(link => {
        const linkPath = link.getAttribute('data-path');
        if (linkPath && (currentPath === linkPath || (linkPath !== '/' && linkPath !== '/index' && currentPath.startsWith(linkPath)))) {
            link.classList.add('active');
        }
    });
});
