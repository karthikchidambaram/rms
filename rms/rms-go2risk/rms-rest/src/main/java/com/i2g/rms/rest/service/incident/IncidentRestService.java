package com.i2g.rms.rest.service.incident;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Address;
import com.i2g.rms.domain.model.Building;
import com.i2g.rms.domain.model.CrimeSuspect;
import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.domain.model.Suspect;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.Witness;
import com.i2g.rms.domain.model.YesNoType;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.domain.model.tablemaintenance.DistinguishingFeatureDetail;
import com.i2g.rms.domain.model.tablemaintenance.ExternalAgency;
import com.i2g.rms.domain.model.tablemaintenance.WeaponType;
import com.i2g.rms.rest.model.AddressRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.SuspectWrapper;
import com.i2g.rms.rest.model.UserRO;
import com.i2g.rms.rest.model.incident.AccidentDetailRO;
import com.i2g.rms.rest.model.incident.AssetDetailRO;
import com.i2g.rms.rest.model.incident.ClaimDetailRO;
import com.i2g.rms.rest.model.incident.CrimeDetailRO;
import com.i2g.rms.rest.model.incident.IncidentDetailRO;
import com.i2g.rms.rest.model.incident.IncidentRO;
import com.i2g.rms.rest.model.incident.InvestigationDetailRO;
import com.i2g.rms.rest.model.incident.LogIncidentRO;
import com.i2g.rms.rest.model.tablemaintenance.DistinguishingFeatureDetailRO;

/**
 * Rest Service for password history rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface IncidentRestService {
	
	/** add flows */
	public List<IncidentRO> get();
	public IncidentRO getIncidentByUniqueIncidentId(final String uniqueIncidentId);
	public UserRO addIncident();
	public IncidentRO logIncident(final LogIncidentRO logIncidentRO);
	public IncidentRO addIncidentDetail(final IncidentDetailRO incidentDetailRO);
	public IncidentRO addAccidentDetail(final AccidentDetailRO accidentDetailRO);
	public IncidentRO addAssetDetail(final AssetDetailRO assetDetailRO);
	public IncidentRO addCrimeDetail(final CrimeDetailRO crimeDetailRO);
	public IncidentRO addClaimDetail(final ClaimDetailRO claimDetailRO);
	public IncidentRO addInvestigationDetail(final InvestigationDetailRO investigationDetailRO);
	public IncidentRO addOrUpdateInvestigationDetail(final InvestigationDetailRO investigationDetailRO);
	
	/** Update Flow */
	public IncidentRO updateLogIncident(final LogIncidentRO logIncidentRO);
	public IncidentRO updateIncidentDetail(final IncidentDetailRO incidentDetailRO);
	
	/** Add or Update Flow */
	public IncidentRO addOrUpdateLogIncident(final LogIncidentRO logIncidentRO);
	public IncidentRO addOrUpdateIncidentDetail(final IncidentDetailRO incidentDetailRO);
	
	/** alternate flows */
	public IncidentRO createSuspectForIncident(final String uniqueIncidentId, final SuspectRO suspectRO);
	public IncidentRO createSuspectsForIncident(final SuspectWrapper suspectWrapper);	
	
	public IncidentRO removeSuspectFromIncident(final String uniqueIncidentId, final Long suspectId);
	public IncidentRO removeSuspectsFromIncident(final SuspectWrapper suspectWrapper);
	
	/** Methods exposed to other services */
	public void validateAnyWitness(final YesNoType anyWitness, 
									final Set<Witness> newWitnesses,
									final Set<Witness> existingWitnesses, 
									final Set<User> employeeWitnesses);
	
	public void validateWeaponInvolvedAndType(final YesNoType weaponInvolved, final WeaponType weaponType);
	
	public void validateExternalAgencyAndType(final YesNoType externalAgencyContacted, 
											final ExternalAgency externalAgency, 
											final LocalDateTime dateTimeContacted);
	
	public Set<Address> constructAddresses(final Set<AddressRO> addressROs, 
											final User user, 
											final Suspect suspect,
											final InjuredPerson injuredPerson, 
											final Witness witness, 
											final CrimeSuspect crimeSuspect,
											final Building building);
	
	public Set<DistinguishingFeatureDetail> constructDistinguishingFeatureDetails(final Set<DistinguishingFeatureDetailRO> distinguishingFeatureDetailROs);
	
	public Set<Suspect> getSuspects(final Set<SuspectRO> suspectROs);
	
	public Set<ReportedLoss> constructReportedLosses(final Set<ReportedLossRO> reportedLossROs, final Incident incident);
}
