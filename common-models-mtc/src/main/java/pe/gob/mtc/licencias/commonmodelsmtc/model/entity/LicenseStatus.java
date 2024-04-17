package pe.gob.mtc.licencias.commonmodelsmtc.model.entity;

public enum LicenseStatus {
    VIGENTE("Vigente"),//Indica que la licencia de conducir está activa y válida para su uso.
    EXPIRADA("Expirada"),//Indica que la licencia de conducir ha caducado y ya no es válida para su uso. Es necesario renovarla para volver a obtener el estado "Vigente".
    SUSPENDIDA("Suspendida"),//Indica que la licencia de conducir ha sido temporalmente suspendida debido a alguna infracción o violación de las leyes de tránsito. Durante este período, el titular de la licencia no puede conducir vehículos.
    REVOCADA("Revocada"),//Indica que la licencia de conducir ha sido revocada y ya no es válida. Esto puede ocurrir debido a múltiples infracciones graves o delitos relacionados con la conducción.
    EN_TRAMITE("En Trámite"),//Indica que la licencia de conducir está en proceso de emisión o renovación. Mientras se encuentra en este estado, la licencia no es válida hasta que se complete el trámite correspondiente.
    ANULADA("Anulada"),//Indica que la licencia de conducir ha sido anulada y ya no tiene validez, generalmente debido a errores en la emisión o a la solicitud del titular de la licencia.
    PERDIDA_O_ROBADA("Perdida o Robada"),//Indica que la licencia de conducir se ha perdido o ha sido robada y es necesario obtener una nueva licencia.
    RESTRINGIDA("Restringida");

    private final String status;

    LicenseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
